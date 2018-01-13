/**
 * Created by ICOP on 2017-10-24.
 */

var PATH = "http://localhost:8080/";
var SHOWPATH = "http://localhost:8081/";
var SHOWCODE = "apps/SampleApp/#";
var CODE = "icop-db";

var MainUrl = {
    LOGIN: PATH + CODE +  "/User/Login",      //登录
    VERIFY: PATH + CODE + "/User/UserVerify", //校验
    REGISTER: PATH + CODE + "/User/Register", //注册
    GETUSERFILES: PATH + CODE + "/Files/getFiles",//获取用户文件列表
    UPLOADFILE: PATH + CODE + "/Files/UploadFile", //上传文件
    NEWFOLDER: PATH + CODE + "Files/newFolder",//新建文件夹


    MAINPATH: SHOWPATH + SHOWCODE + "/DB", //首页地址
}
module.exports = MainUrl;