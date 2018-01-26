/**
 * Created by ICOP on 2017-10-24.
 */

var RestUrl = require('../../actions/RestUrl');

var ADDR = RestUrl.ADDR;
var ROOT_PATH = RestUrl.ROOT_PATH;

var EnterTheUrl = {
    LOGIN: ADDR + ROOT_PATH +  "/User/Login",      //登录
    VERIFY: ADDR + ROOT_PATH + "/User/UserVerify", //校验
    REGISTER: ADDR + ROOT_PATH + "/User/Register", //注册
}
module.exports = EnterTheUrl;