/**
 * Created by ICOP on 2017-10-24.
 */

var RestUrl = require('../../actions/RestUrl');

var ADDR = RestUrl.ADDR;
var ROOT_PATH = RestUrl.ROOT_PATH;

var MainUrl = {
    SPACESTORAGE : ADDR + ROOT_PATH + '/Files/getUserFileTypeNumber', //用户存储空间使用情况
    UPDATEUSERMESSAGE: ADDR + ROOT_PATH + '/User/updateUser',//更新用户信息
    ALTERUSERPWD: ADDR + ROOT_PATH + '/User/alterUserPwd',//修改用户密码
}
module.exports = MainUrl;