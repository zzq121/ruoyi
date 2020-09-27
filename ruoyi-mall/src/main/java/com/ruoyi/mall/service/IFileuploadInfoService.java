package com.ruoyi.mall.service;

import com.ruoyi.mall.domain.FileuploadInfo;

import java.util.List;

/**
 * 文件上传Service接口
 * 
 * @author gaofeng
 * @date 2020-09-08
 */
public interface IFileuploadInfoService 
{
    /**
     * 查询文件上传
     * 
     * @param fileId 文件上传ID
     * @return 文件上传
     */
    public FileuploadInfo selectFileuploadInfoById(Long fileId);

    /**
     * 查询文件上传列表
     * 
     * @param fileuploadInfo 文件上传
     * @return 文件上传集合
     */
    public List<FileuploadInfo> selectFileuploadInfoList(FileuploadInfo fileuploadInfo);

    /**
     * 新增文件上传
     * 
     * @param fileuploadInfo 文件上传
     * @return 结果
     */
    public int insertFileuploadInfo(FileuploadInfo fileuploadInfo);

    /**
     * 修改文件上传
     * 
     * @param fileuploadInfo 文件上传
     * @return 结果
     */
    public int updateFileuploadInfo(FileuploadInfo fileuploadInfo);

    /**
     * 批量删除文件上传
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileuploadInfoByIds(String ids);

    /**
     * 删除文件上传信息
     * 
     * @param fileId 文件上传ID
     * @return 结果
     */
    public int deleteFileuploadInfoById(Long fileId);
}
