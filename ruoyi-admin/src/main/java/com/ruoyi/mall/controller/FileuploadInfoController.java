package com.ruoyi.mall.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mall.domain.FileuploadInfo;
import com.ruoyi.mall.service.IFileuploadInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件上传Controller
 * 
 * @author gaofeng
 * @date 2020-09-08
 */
@Controller
@RequestMapping("/mall/uploadinfo")
public class FileuploadInfoController extends BaseController
{
    private String prefix = "mall/uploadinfo";

    @Autowired
    private IFileuploadInfoService fileuploadInfoService;

    @RequiresPermissions("mall:uploadinfo:view")
    @GetMapping()
    public String uploadinfo()
    {
        return prefix + "/uploadinfo";
    }


    /**
     * 查询文件上传列表
     */
    @RequiresPermissions("mall:uploadinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FileuploadInfo fileuploadInfo)
    {
        startPage();
        List<FileuploadInfo> list = fileuploadInfoService.selectFileuploadInfoList(fileuploadInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件上传列表
     */
    @RequiresPermissions("mall:uploadinfo:export")
    @Log(title = "文件上传", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FileuploadInfo fileuploadInfo)
    {
        List<FileuploadInfo> list = fileuploadInfoService.selectFileuploadInfoList(fileuploadInfo);
        ExcelUtil<FileuploadInfo> util = new ExcelUtil<FileuploadInfo>(FileuploadInfo.class);
        return util.exportExcel(list, "uploadinfo");
    }

    /**
     * 新增文件上传
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文件上传
     */
    @RequiresPermissions("mall:uploadinfo:add")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FileuploadInfo fileuploadInfo)
    {
        return toAjax(fileuploadInfoService.insertFileuploadInfo(fileuploadInfo));
    }
    /**
     * 下载
     */
    @GetMapping("/download/{fileId}")
    public void resourceDownload(@PathVariable("fileId") Long fileId, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        FileuploadInfo fileuploadInfo = fileuploadInfoService.selectFileuploadInfoById(fileId);
        String fileName = fileuploadInfo.getFileName();
        String filePath = fileuploadInfo.getFilePath();
        String realName = fileName+filePath.substring(filePath.indexOf("."));

        String path = Global.getUploadPath()+filePath.replace("http://localhost:8080/profile/upload","");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realName));
        FileUtils.writeBytes(path, response.getOutputStream());
    }

    /**
     * 修改文件上传
     */
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap)
    {
        FileuploadInfo fileuploadInfo = fileuploadInfoService.selectFileuploadInfoById(fileId);
        mmap.put("fileuploadInfo", fileuploadInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件上传
     */
    @RequiresPermissions("mall:uploadinfo:edit")
    @Log(title = "文件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FileuploadInfo fileuploadInfo)
    {
        return toAjax(fileuploadInfoService.updateFileuploadInfo(fileuploadInfo));
    }

    /**
     * 删除文件上传
     */
    @RequiresPermissions("mall:uploadinfo:remove")
    @Log(title = "文件上传", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fileuploadInfoService.deleteFileuploadInfoByIds(ids));
    }
}
