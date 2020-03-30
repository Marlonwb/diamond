package person.marlon.diamond.service.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import person.marlon.diamond.common.util.ResourceUtil;

import java.io.File;

@Service("fileService")
public class FileService {
    
    /**
     * 文件上传
     * @param mpfList   文件信息集
     * @param fileRecord  需要记录的参数，客户端传入
     * @param extraPath  额外的路径，首部和结尾不能带斜杠'/'
     * @return
     */
//    public ApiResponse<String> uploadFile(List<MultipartFile> mpfList, FileRecord fileRecord, String extraPath) {
//        // 信息验证begin
//        if (mpfList == null || mpfList.isEmpty() || !FileUploadValidator.validateParam(fileRecord)) {
//            return new ErrorResult(RetCode.ILLEGAL_ARGS, "Illegal args");
//        }
//        // 获取对应业务文件配置信息
//        FileConf fileConf = this.fileConfDao.selectByBizType(fileRecord.getBizType());
//        if(fileConf == null){
//            log.info("file conf is null");  // 打印文件配置信息
//            return new ErrorResult(RetCode.FILE_CONF_ERROR, "file conf not exist");
//        }
//        log.info(fileConf.toString());  // 打印文件配置信息
//        // 验证文件信息是否符合配置信息
//        ErrorResult errorRes = FileUploadValidator.validateFileInfo(mpfList, fileConf);
//        if (errorRes != null) {
//            return errorRes;  // 验证失败,直接返回
//        }
//        // 信息验证end
//        List<FileInfo> files = new ArrayList<>();
//        FileInfo fileInfo = null;
//        String prdfix, suffix;  // 文件名称前缀和后缀
//        String path = fileConf.getPath();  // 文件存储的目录
//        if(StringUtil.isEmpty(path) ){
//            return new ErrorResult(RetCode.DIRECTORY_UNMOUNTED, "Hard disk not mounted");
//        }
//
//        // 获取相对路径，由file_conf、额外路径、加入上传日期yyyy-mm-dd( /path/extraPath/2011-11-11/ )
//        String relativePath = fileConf.getResourceRealm() + "/"
//                + (StringUtil.isEmpty(extraPath) ? "" : extraPath + "/");
//        //  + TimeUtil.getCurrentDateString(TimeUtil.DEFAULT_DAY_FORMAT) + "/";
//
//        // 验证服务器存储路径是否存在，若不存在，则新建文件夹
//        File serFile = new File(path + relativePath);
//        if (!serFile.exists()) {
//            serFile.mkdirs();
//        }
//
//        // 校验path是否被装载：装载失败访问会出错（没有读写权限）
//        if(!serFile.canWrite()){
//            return new ErrorResult(RetCode.DIRECTORY_UNMOUNTED, "Hard disk not mounted");
//        }
//
//        // 循环上传文件
//        int suffixIndex;  // 截取文件扩展名的开始下标
//        Date begin = new Date();  // 记录开始时间
//        int mpIndex = 0, mpSize = mpfList.size();
//        MultipartFile mpf = null;
//        // todo 记录异常(空间不足)重新上传的文件信息下标记录，防止出现循环异常造成无限循环
//        Set<Integer> uploadAgainMpIndexSet = new HashSet<Integer>();
//        while (mpIndex < mpSize) {
//            mpf = mpfList.get(mpIndex);
//
//            /*begin文件名超出256个字节时，截取256字节*/
//            String originalFileName = mpf.getOriginalFilename(); // 获取源文件名
//            suffixIndex = originalFileName.lastIndexOf(".");
//            if (suffixIndex > 0) {
//                prdfix = originalFileName.substring(0, suffixIndex); // 文件名前缀
//                suffix = originalFileName.substring(suffixIndex);  // 文件名后缀
//            } else {
//                prdfix = originalFileName; // 文件名前缀
//                suffix = "";   // 文件名后缀
//            }
//            suffix = StringUtil.subStringByBytes(suffix, 100);  // 后缀,文件后缀名最长100字节
//            prdfix = StringUtil.subStringByBytes(prdfix, 240 - StringUtil.getStrBytesLength(suffix));  // 前缀
//            /* end */
//
//            originalFileName = prdfix + suffix;
//
//            // 验证改文件是否已经存在，如果已经存在则加上时间戳
//            if (new File(path + relativePath + originalFileName).exists()) {
//                originalFileName = prdfix +  "_" + System.currentTimeMillis() + suffix;
//            }
//            // 组装数据
//            fileInfo = new FileInfo();
//            fileInfo.setOriginalName(mpf.getOriginalFilename());
//            fileInfo.setFileSize(String.valueOf(mpf.getSize() / 1024)); // 单位（kb）
//            fileInfo.setFileType(mpf.getContentType());     // 文件类型
//            // 生成新文件名
//            // String newFileName = FileUtil.getNewFileName() + originalFileName.substring(originalFileName.indexOf("."));
//            String newFileName = originalFileName; // 暂时使用原名字，如何原名称已经存在则加时间戳
//
//            fileInfo.setNewName(newFileName);                        // 文件新名字
//            fileInfo.setCreateTime(new Date());                     // 创建时间
//            fileInfo.setLastUpdateTime(fileInfo.getCreateTime());    // 最后修改时间
//            fileInfo.setRelativePath(relativePath + newFileName);    // 文件相对路径
//            fileInfo.setFilePath(path + relativePath + newFileName); // 文件物理路径
//            // 存储文件
//            try {
//                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(fileInfo.getFilePath()));
//                fileInfoDao.insert(fileInfo);
//                // 记录
//                fileRecord.setFileInfoId(fileInfo.getId());
//                fileRecordDao.insert(fileRecord);
//                files.add(fileInfo);
//                mpIndex++;   // 循环下一个MultipartFile
//            } catch (IOException e) {
//                File file = new File(fileInfo.getFilePath());
//                if (file.isFile() && file.exists()) {  // 删除上传失败的文件
//                    file.delete();
//                }
//                // todo begin空间不足时处理方法,  每个MultipartFile只能重新上传一次
//                if ("No space left on device".equals(e.getMessage()) && !uploadAgainMpIndexSet.contains(mpIndex)) {
//                    noSpaceLeftOnDevice(serFile, mpf.getSize(), begin);  // 设备空间不足时，删除最早上传的文件内容，详情查看方法注释
//                    uploadAgainMpIndexSet.add(mpIndex);
//                    // end
//                } else {
//                    log.error("upload file error!", e);
//                    return new ErrorResult(RetCode.FILE_UPLOAD_FAILED, e.getMessage());
//                   /* if (mpSize > 1) {  // 多文件上传时，部分文件上传失败不抛出异常
//                        fileInfo.setRelativePath(null);
//                        files.add(fileInfo);
//                        mpIndex++;   // 循环下一个MultipartFile
//                    } else {
//                        return new ErrorResult(RetCode.FILE_UPLOAD_FAILED, e.getMessage());
//                    }*/
//                }
//            }
//        }
//
//        // 上传成功 todo 请不要改变这个成功的类型ListResult
//        return new ListResult(files);
//    }
    
    private Logger logger = LoggerFactory.getLogger(FileService.class);
    
    private final String UPLOAD_FILE_PATH = ResourceUtil.getValue("upload_file_path");
    private final String UPLOAD_FILE_ROOT_DIR = ResourceUtil.getValue("upload_file_root_dir");

    public boolean writeFile(MultipartFile file,String newFilePrefix){
        if(file == null){
            logger.info("failed to upload file because the file was null.");
            return false;
        }
        if(file.isEmpty()){
            logger.info("failed to upload " +  file.getOriginalFilename() + " because the file was empty.");
            return false;
        }
        
        try {
            // 文件存放服务端的位置
            File dir = new File(UPLOAD_FILE_PATH + File.separator + UPLOAD_FILE_ROOT_DIR);
            if (!dir.exists())
                dir.mkdirs();
            // 写文件到服务器
            File serverFile = new File(dir.getAbsolutePath() + File.separator + newFilePrefix + file.getOriginalFilename());
            file.transferTo(serverFile);//该方法重复提交会删除旧的同名图片
            logger.info("You successfully uploaded file : " +  file.getOriginalFilename());
            return true;
        } catch (Exception e) {
            logger.info("Failed to upload " +  file.getOriginalFilename() + " => " + e.getMessage());
            return false;
        }
    }
}
