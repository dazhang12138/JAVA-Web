//预警级别 显示图片处理器
var RestUrl = require('../../actions/RestUrl');

function WarningGradeImageHandler() {
    //文件夹
    var folders = 'folder-open';
    //图片
    var pic = 'picture';
    //视频
    var video = 'video-camera';
    //音乐
    var music = 'customerservice';
    //种子
    var seed = 'file-pdf';
    //文档
    var doc = 'file-text';
    //其他
    var other = 'file-unknown';


    this.getFileTypeImage=function (type,file) {//获取预警图片链接
        if(type == 0){
            return folders;
        }else{
            if(file == 'PICTURE'){
                return pic;
            }else if(file == 'VIDEO'){
                return video;
            }else if(file == 'MUSIC'){
                return music;
            }else if(file == 'SEED'){
                return seed;
            }else if(file == 'DOCUMENTS'){
                return doc;
            }else{
                return other;
            }
        }
    }
}
module.exports = WarningGradeImageHandler;