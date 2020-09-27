package com.ruoyi.mall.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.mall.domain.FileuploadInfo;
import com.ruoyi.mall.mapper.FileuploadInfoMapper;
import com.ruoyi.mall.service.IFileuploadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件上传Service业务层处理
 * 
 * @author gaofeng
 * @date 2020-09-08
 */
@Service
public class FileuploadInfoServiceImpl implements IFileuploadInfoService 
{
    @Autowired
    private FileuploadInfoMapper fileuploadInfoMapper;

    /**
     * 查询文件上传
     * 
     * @param fileId 文件上传ID
     * @return 文件上传
     */
    @Override
    public FileuploadInfo selectFileuploadInfoById(Long fileId)
    {
        return fileuploadInfoMapper.selectFileuploadInfoById(fileId);
    }

    /**
     * 查询文件上传列表
     * 
     * @param fileuploadInfo 文件上传
     * @return 文件上传
     */
    @Override
    public List<FileuploadInfo> selectFileuploadInfoList(FileuploadInfo fileuploadInfo)
    {
        return fileuploadInfoMapper.selectFileuploadInfoList(fileuploadInfo);
    }

    /**
     * 新增文件上传
     * 
     * @param fileuploadInfo 文件上传
     * @return 结果
     */
    @Override
    public int insertFileuploadInfo(FileuploadInfo fileuploadInfo)
    {
        return fileuploadInfoMapper.insertFileuploadInfo(fileuploadInfo);
    }

    /**
     * 修改文件上传
     * 
     * @param fileuploadInfo 文件上传
     * @return 结果
     */
    @Override
    public int updateFileuploadInfo(FileuploadInfo fileuploadInfo)
    {
        return fileuploadInfoMapper.updateFileuploadInfo(fileuploadInfo);
    }

    /**
     * 删除文件上传对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFileuploadInfoByIds(String ids)
    {
        return fileuploadInfoMapper.deleteFileuploadInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件上传信息
     * 
     * @param fileId 文件上传ID
     * @return 结果
     */
    @Override
    public int deleteFileuploadInfoById(Long fileId)
    {
        return fileuploadInfoMapper.deleteFileuploadInfoById(fileId);
    }
}
