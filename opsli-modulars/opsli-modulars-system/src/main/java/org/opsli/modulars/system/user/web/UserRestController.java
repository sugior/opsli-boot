/**
 * Copyright 2020 OPSLI 快速开发平台 https://www.opsli.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.opsli.modulars.system.user.web;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.util.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.opsli.api.base.result.ResultVo;
import org.opsli.api.web.system.user.UserApi;
import org.opsli.api.wrapper.system.menu.MenuModel;
import org.opsli.api.wrapper.system.options.OptionsModel;
import org.opsli.api.wrapper.system.user.*;
import org.opsli.common.annotation.ApiRestController;
import org.opsli.common.annotation.EnableLog;
import org.opsli.common.annotation.RequiresPermissionsCus;
import org.opsli.common.constants.MyBatisConstants;
import org.opsli.common.enums.DictType;
import org.opsli.common.exception.ServiceException;
import org.opsli.common.exception.TokenException;
import org.opsli.common.utils.FieldUtil;
import org.opsli.common.utils.ListDistinctUtil;
import org.opsli.common.utils.WrapperUtil;
import org.opsli.core.base.controller.BaseRestController;
import org.opsli.core.msg.TokenMsg;
import org.opsli.core.persistence.Page;
import org.opsli.core.persistence.querybuilder.GenQueryBuilder;
import org.opsli.core.persistence.querybuilder.QueryBuilder;
import org.opsli.core.persistence.querybuilder.WebQueryBuilder;
import org.opsli.core.persistence.querybuilder.conf.WebQueryConf;
import org.opsli.core.utils.OptionsUtil;
import org.opsli.core.utils.OrgUtil;
import org.opsli.core.utils.UserUtil;
import org.opsli.modulars.system.SystemMsg;
import org.opsli.modulars.system.org.service.ISysOrgService;
import org.opsli.modulars.system.user.entity.SysUser;
import org.opsli.modulars.system.user.entity.SysUserWeb;
import org.opsli.modulars.system.user.service.IUserService;
import org.opsli.plugins.oss.OssStorageFactory;
import org.opsli.plugins.oss.service.BaseOssStorageService;
import org.opsli.plugins.oss.service.OssStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;


/**
 * 用户管理 Controller
 *
 * @author Parker
 * @date 2020-09-16 17:33
 */
@Api(tags = UserApi.TITLE)
@Slf4j
@ApiRestController("/system/user")
public class UserRestController extends BaseRestController<SysUser, UserModel, IUserService>
        implements UserApi {


    /**
     * 当前登陆用户信息
     * @return ResultVo
     */
    @ApiOperation(value = "当前登陆用户信息", notes = "当前登陆用户信息")
    @Override
    public ResultVo<UserInfo> getInfo(HttpServletRequest request) {
        UserModel user = UserUtil.getUser();
        return this.getInfoById(user.getId());
    }

    /**
     * 当前登陆用户信息 By Id
     * @return ResultVo
     */
    @ApiOperation(value = "当前登陆用户信息 By Id", notes = "当前登陆用户信息 By Id")
    @Override
    public ResultVo<UserInfo> getInfoById(String userId) {
        UserModel user = UserUtil.getUser(userId);
        if(user == null){
            throw new TokenException(TokenMsg.EXCEPTION_TOKEN_LOSE_EFFICACY);
        }

        List<String> userRolesByUserId = UserUtil.getUserRolesByUserId(user.getId());
        List<String> userAllPermsByUserId = UserUtil.getUserAllPermsByUserId(user.getId());

        UserInfo userInfo = WrapperUtil.transformInstance(user, UserInfo.class);
        userInfo.setRoles(userRolesByUserId);
        userInfo.setPerms(userAllPermsByUserId);

        // 判断是否是超级管理员
        if(StringUtils.equals(UserUtil.SUPER_ADMIN, user.getUsername())){
            userInfo.setIzSuperAdmin(true);
        }

        return ResultVo.success(userInfo);
    }


    /**
     * 当前登陆用户组织机构
     * @return ResultVo
     */
    @ApiOperation(value = "当前登陆用户组织机构", notes = "当前登陆用户组织机构")
    @Override
    public ResultVo<?> getOrg() {
        UserModel user = UserUtil.getUser();
        return this.getOrgByUserId(user.getId());
    }

    /**
     * 用户组织机构
     * @param userId 用户ID
     * @return ResultVo
     */
    @ApiOperation(value = "用户组织机构", notes = "用户组织机构")
    @Override
    public ResultVo<?> getOrgByUserId(String userId) {
        List<UserOrgRefModel> orgListByUserId = OrgUtil.getOrgListByUserId(userId);
        return ResultVo.success(orgListByUserId);
    }

    /**
     * 根据 userId 获得用户角色Id集合
     * @param userId 用户Id
     * @return ResultVo
     */
    @ApiOperation(value = "根据 userId 获得用户角色Id集合", notes = "根据 userId 获得用户角色Id集合")
    @Override
    public ResultVo<List<String>> getRoleIdsByUserId(String userId) {
        List<String> roleIdList = IService.getRoleIdList(userId);
        return ResultVo.success(roleIdList);
    }



    /**
     * 修改密码
     * @return ResultVo
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @Override
    public ResultVo<?> updatePassword(UserPassword userPassword) {
        // 演示模式 不允许操作
        super.demoError();

        UserModel user = UserUtil.getUser();
        userPassword.setUserId(user.getId());
        IService.updatePassword(userPassword);
        return ResultVo.success();
    }

    /**
     * 上传头像
     * @param request 文件流 request
     * @return ResultVo
     */
    @ApiOperation(value = "上传头像", notes = "上传头像")
    @Override
    public ResultVo<?> updateAvatar(MultipartHttpServletRequest request) {
        Iterator<String> itr = request.getFileNames();
        String uploadedFile = itr.next();
        List<MultipartFile> files = request.getFiles(uploadedFile);
        if (CollectionUtils.isEmpty(files)) {
            // 请选择文件
            return ResultVo.error(SystemMsg.EXCEPTION_USER_FILE_NULL.getCode(),
                    SystemMsg.EXCEPTION_USER_FILE_NULL.getMessage());
        }

        try {
            // 调用OSS 服务保存头像
            OssStorageService ossStorageService = OssStorageFactory.INSTANCE.getHandle();
            BaseOssStorageService.FileAttr fileAttr = ossStorageService.upload(
                    files.get(0).getInputStream(), "jpg");

            UserModel user = UserUtil.getUser();
            // 更新头像至数据库
            UserModel userModel = new UserModel();
            userModel.setId(user.getId());
            userModel.setAvatar(fileAttr.getFileStoragePath());
            IService.updateAvatar(userModel);
            // 刷新用户信息
            UserUtil.refreshUser(user);
        }catch (IOException e){
            log.error(e.getMessage(), e);
            return ResultVo.error("更新头像失败，请稍后再试");
        }

        return ResultVo.success();
    }

    // ==================================================


    /**
     * 修改密码
     * @return ResultVo
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequiresPermissions("system_user_updatePassword")
    @EnableLog
    @Override
    public ResultVo<?> updatePasswordById(UserPassword userPassword) {
        // 演示模式 不允许操作
        super.demoError();

        IService.updatePassword(userPassword);
        return ResultVo.success();
    }

    /**
     * 重置密码
     * @return ResultVo
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @RequiresPermissions("system_user_resetPassword")
    @EnableLog
    @Override
    public ResultVo<?> resetPasswordById(String userId) {
        // 演示模式 不允许操作
        super.demoError();

        // 配置文件默认密码
        String defPass = globalProperties.getAuth().getDefaultPass();

        // 缓存默认密码 优先缓存
        OptionsModel optionsModel = OptionsUtil.getOptionByCode("def_pass");
        if(optionsModel != null){
            defPass = optionsModel.getOptionValue();
        }

        UserPassword userPassword = new UserPassword();
        userPassword.setNewPassword(defPass);
        userPassword.setUserId(userId);

        boolean resetPasswordFlag = IService.resetPassword(userPassword);
        if(!resetPasswordFlag){
            return ResultVo.error("重置密码失败");
        }

        return ResultVo.success("重置密码成功！默认密码为：" + defPass);
    }

    /**
     * 变更账户状态
     * @return ResultVo
     */
    @ApiOperation(value = "锁定账户", notes = "锁定账户")
    @RequiresPermissions("system_user_enable")
    @EnableLog
    @Override
    public ResultVo<?> enableAccount(String userId, String enable) {
        // 演示模式 不允许操作
        super.demoError();

        // 变更账户状态
        boolean lockAccountFlag = IService.enableAccount(userId, enable);
        if(!lockAccountFlag){
            return ResultVo.error("变更用户状态失败");
        }
        return ResultVo.success();
    }


    /**
     * 用户信息 查一条
     * @param model 模型
     * @return ResultVo
     */
    @ApiOperation(value = "获得单条用户信息", notes = "获得单条用户信息 - ID")
    // 因为工具类 使用到该方法 不做权限验证
    //@RequiresPermissions("system_user_select")
    @Override
    public ResultVo<UserModel> get(UserModel model) {
        // 如果系统内部调用 则直接查数据库
        if(model != null && model.getIzApi() != null && model.getIzApi()){
            model = IService.get(model);
        }
        return ResultVo.success(model);
    }

    /**
     * 用户信息 查询分页
     * @param pageNo 当前页
     * @param pageSize 每页条数
     * @param orgIdGroup 组织ID组
     * @param request request
     * @return ResultVo
     */
    @ApiOperation(value = "获得分页数据", notes = "获得分页数据 - 查询构造器")
    @RequiresPermissions("system_user_select")
    @Override
    public ResultVo<?> findPage(Integer pageNo, Integer pageSize,
                                 String orgIdGroup,
                                 HttpServletRequest request) {
        QueryBuilder<SysUserWeb> queryBuilder = new WebQueryBuilder<>(
                SysUserWeb.class, request.getParameterMap());
        Page<SysUserWeb, UserWebModel> page = new Page<>(pageNo, pageSize);
        QueryWrapper<SysUserWeb> queryWrapper = queryBuilder.build();

        // 处理组织权限
        OrgUtil.handleOrgIdGroupCondition(orgIdGroup, queryWrapper);

        page.setQueryWrapper(queryWrapper);
        page = IService.findPageByCus(page);
        // 密码防止分页泄露处理
        for (UserWebModel userModel : page.getList()) {
            userModel.setSecretKey(null);
            userModel.setPassword(null);
            userModel.setPasswordLevel(null);
        }
        return ResultVo.success(page.getPageData());
    }

    /**
     * 用户信息 新增
     * @param model 模型
     * @return ResultVo
     */
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    @RequiresPermissions("system_user_insert")
    @EnableLog
    @Override
    public ResultVo<?> insert(UserModel model) {
        // 调用新增方法
        IService.insert(model);
        return ResultVo.success("新增用户信息成功");
    }

    /**
     * 用户信息 修改
     * @param model 模型
     * @return ResultVo
     */
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @RequiresPermissions("system_user_update")
    @EnableLog
    @Override
    public ResultVo<?> update(UserModel model) {
        // 演示模式 不允许操作
        super.demoError();
        // 调用修改方法
        IService.update(model);
        return ResultVo.success("修改用户信息成功");
    }

    /**
     * 用户信息 自身修改
     * @param model 模型
     * @return ResultVo
     */
    @ApiOperation(value = "修改自身用户信息", notes = "修改自身用户信息")
    @EnableLog
    @Override
    public ResultVo<?> updateSelf(UserModel model) {
        UserModel currUser = UserUtil.getUser();
        if(!StringUtils.equals(currUser.getId(), model.getId())){
            // 非法参数 防止其他用户 通过该接口 修改非自身用户数据
            throw new ServiceException(SystemMsg.EXCEPTION_USER_ILLEGAL_PARAMETER);
        }
        // 调用修改方法
        IService.update(model);
        return ResultVo.success("修改用户信息成功");
    }

    /**
     * 用户信息 删除
     * @param id ID
     * @return ResultVo
     */
    @ApiOperation(value = "删除用户信息数据", notes = "删除用户信息数据")
    @RequiresPermissions("system_user_delete")
    @EnableLog
    @Override
    public ResultVo<?> del(String id){
        // 演示模式 不允许操作
        super.demoError();

        IService.delete(id);

        return ResultVo.success("删除用户信息成功");
    }


    /**
     * 用户信息 批量删除
     * @param ids ID 数组
     * @return ResultVo
     */
    @ApiOperation(value = "批量删除用户信息数据", notes = "批量删除用户信息数据")
    @RequiresPermissions("system_user_delete")
    @EnableLog
    @Override
    public ResultVo<?> delAll(String ids){
        // 演示模式 不允许操作
        super.demoError();

        String[] idArray = Convert.toStrArray(ids);
        IService.deleteAll(idArray);

        return ResultVo.success("批量删除用户信息成功");
    }


    /**
     * 用户信息 Excel 导出
     * @param request request
     * @param response response
     */
    @ApiOperation(value = "导出Excel", notes = "导出Excel")
    @RequiresPermissionsCus("system_user_export")
    @EnableLog
    @Override
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        // 当前方法
        Method method = ReflectUtil.getMethodByName(this.getClass(), "exportExcel");
        QueryBuilder<SysUser> queryBuilder = new WebQueryBuilder<>(entityClazz, request.getParameterMap());
        super.excelExport(UserApi.SUB_TITLE, queryBuilder.build(), response, method);
    }

    /**
     * 用户信息 Excel 导入
     * @param request 文件流 request
     * @return ResultVo
     */
    @ApiOperation(value = "导入Excel", notes = "导入Excel")
    @RequiresPermissions("system_user_import")
    @EnableLog
    @Override
    public ResultVo<?> importExcel(MultipartHttpServletRequest request) {
        return super.importExcel(request);
    }

    /**
     * 用户信息 Excel 下载导入模版
     * @param response response
     */
    @ApiOperation(value = "导出Excel模版", notes = "导出Excel模版")
    @RequiresPermissionsCus("system_user_import")
    @Override
    public void importTemplate(HttpServletResponse response) {
        // 当前方法
        Method method = ReflectUtil.getMethodByName(this.getClass(), "importTemplate");
        super.importTemplate(UserApi.SUB_TITLE, response, method);
    }

    /**
     * 根据 username 获得用户
     * @param username 用户名
     * @return ResultVo
     */
    @ApiOperation(value = "根据 username 获得用户", notes = "根据 username 获得用户")
    @Override
    public ResultVo<UserModel> getUserByUsername(String username) {
        UserModel userModel = IService.queryByUserName(username);
        if(userModel == null){
            // 暂无该用户
            throw new ServiceException(SystemMsg.EXCEPTION_USER_NULL.getCode(),
                    StrUtil.format(SystemMsg.EXCEPTION_USER_NULL.getMessage(), username)
            );
        }
        return ResultVo.success(userModel);
    }

    /**
     * 根据 userId 获得用户角色
     * @param userId 用户Id
     * @return ResultVo
     */
    @Override
    public ResultVo<List<String>> getRolesByUserId(String userId) {
        List<String> roleCodeList = IService.getRoleCodeList(userId);
        return ResultVo.success(roleCodeList);
    }

    /**
     * 根据 userId 获得用户权限
     * @param userId 用户Id
     * @return ResultVo
     */
    @Override
    public ResultVo<List<String>> getAllPerms(String userId) {
        List<String> allPerms = IService.getAllPerms(userId);
        return ResultVo.success(allPerms);
    }


    /**
     * 根据 userId 获得用户菜单
     * @param userId 用户Id
     * @return ResultVo
     */
    @Override
    public ResultVo<List<MenuModel>> getMenuListByUserId(String userId) {
        List<MenuModel> menuModelList = IService.getMenuListByUserId(userId);
        return ResultVo.success(menuModelList);
    }


    /**
     * 用户组织机构
     * @param userId 用户ID
     * @return ResultVo
     */
    @ApiOperation(value = "用户组织机构", notes = "用户组织机构")
    @Override
    public ResultVo<UserOrgRefWebModel> getOrgInfoByUserId(String userId) {
        UserOrgRefWebModel org = null;
        // 不写SQL了 直接分页 第一页 取第一条
        QueryBuilder<SysUserWeb> queryBuilder = new GenQueryBuilder<>();
        Page<SysUserWeb, UserWebModel> page = new Page<>(1, 1);
        QueryWrapper<SysUserWeb> queryWrapper = queryBuilder.build();
        queryWrapper.eq(
                "a.id",
                userId
        );
        page.setQueryWrapper(queryWrapper);
        page = IService.findPageByCus(page);
        List<UserWebModel> list = page.getList();
        if(CollUtil.isNotEmpty(list)){
            UserWebModel userWebModel = list.get(0);
            if(userWebModel != null){
//                org  = userAndOrgModel.getOrg();
//                if(org != null){
//
//                    org.setUserId(userId);

//                    List<String> orgIds = Lists.newArrayListWithCapacity(3);
//                    orgIds.add(org.getCompanyId());
//                    orgIds.add(org.getDepartmentId());
//                    orgIds.add(org.getPostId());
//                    QueryWrapper<SysOrg> orgQueryWrapper = new QueryWrapper<>();
//                    orgQueryWrapper.in(
//                            FieldUtil.humpToUnderline(MyBatisConstants.FIELD_ID),
//                            orgIds);
//                    List<SysOrg> orgList = iSysOrgService.findList(orgQueryWrapper);
//                    if(CollUtil.isNotEmpty(orgList)){
//                        Map<String, SysOrg> tmp = Maps.newHashMap();
//                        for (SysOrg sysOrg : orgList) {
//                            tmp.put(sysOrg.getId(), sysOrg);
//                        }
//
//                        // 设置 名称
//                        SysOrg company = tmp.get(org.getCompanyId());
//                        if(company != null){
//                            org.setCompanyName(company.getOrgName());
//                        }
//
//                        SysOrg department = tmp.get(org.getDepartmentId());
//                        if(department != null){
//                            org.setDepartmentName(department.getOrgName());
//                        }
//
//                        SysOrg post = tmp.get(org.getPostId());
//                        if(post != null){
//                            org.setPostName(post.getOrgName());
//                        }
//                    }

//                }
            }
        }
        return ResultVo.success(org);
    }
}
