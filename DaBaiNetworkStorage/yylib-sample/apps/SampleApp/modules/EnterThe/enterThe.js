var React = require('react');
var _ = require('lodash');
var {YYClass,YYMessage} = require('yylib-ui');
var {YYCreatePage} =  require('yylib-business');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var URL = require('./Url');
var cardKeys = require('./CardsKeys');
var RegisterUserName = true;
var RegisterName = true;
var _this;
//页面初始化
var EventHanlder = {
    "backSumbitBut":{
        onClick:function () {
            _this.findUI('backForm').api.validateFields(function (errors, values) {
               if(!!errors){
                   YYMessage.error('找回失败!请填写邮箱信息');
                   return;
               }
            });
        }
    },
    "registerSumbitBut":{
        onClick:function () {
            _this.findUI('registerForm').api.validateFields(function (errors, values) {
                if (!!errors) {
                    YYMessage.error('注册失败!请正确填写注册信息!');
                    return;
                }
                if(values.reUserpwd != values.reUserpwd2){
                    YYMessage.error('注册失败!两次密码输入不匹配!');
                    return;
                }
                if(RegisterName){
                    YYMessage.error('注册失败!此昵称已被占用!');
                    return;
                }
                if(RegisterUserName){
                    YYMessage.error('注册失败!此用户名已被占用!');
                    return;
                }
                var data = {
                    userName : values.reUserName,
                    userPwd : values.reUserpwd,
                    name:values.reName,
                    userEmail:values.reUserEmail
                };
                ajax.postJSON(URL.REGISTER,data,function (result) {
                    if(result){
                        YYMessage.success('注册成功！');
                    }else{
                        YYMessage.error('注册失败!');
                    }
                });
            })
        }
    },
    "loginSumbitBut":{
        onClick:function () {
            _this.findUI('loginFrom').api.validateFields(function (error, values) {
                if(!!error){
                    YYMessage.error("登录失败");
                    return;
                }
                var data = {
                    userName : values.loginUserName,
                    userPwd : values.loginUserPwd
                };
                ajax.postJSON(URL.LOGIN,data,function (user) {
                    var result = user.result;
                    if(result == 'error'){
                        YYMessage.error('用户名或密码不正确！请重新输入或找回密码!');
                    }else{
                        _this.routeTo("DB/mainPage",null,user);
                    }
                });
            })
        }
    },
    "loginResetBut":{
        onClick:function () {
            _this.findUI('loginFrom').api.resetFields();
        }
    },
    "registerResetBut":{
        onClick:function () {
            _this.findUI('registerForm').api.resetFields();
        }
    },
    "backResetBut":{
        onClick:function () {
            _this.findUI('backForm').api.resetFields();
        }
    },
    "card":{
        onChange:function (keys) {
            if(keys == cardKeys.LOGIN){
                var loginUserPwd = document.getElementById("loginUserPwd");
                loginUserPwd.type = 'password';
            }else if(keys == cardKeys.REGISTER){
                var userpwd = document.getElementById("reUserpwd");
                userpwd.type = 'password';
                var userpwd2 = document.getElementById("reUserpwd2");
                userpwd2.type = 'password';
                var reName = document.getElementById("reName");
                reName.onchange = function () {
                    var data = {
                        name:reName.value
                    };
                    ajax.getJSON(URL.VERIFY,data,function (result) {
                        if (result) {
                            YYMessage.info("抱歉，该昵称已被占用。请重新填写!");
                            RegisterName = true;
                        } else {
                            RegisterName = false;
                        }
                    });
                };
                var reUserName = document.getElementById('reUserName');
                reUserName.onchange = function () {
                    var data = {
                        userName:reUserName.value
                    };
                    ajax.getJSON(URL.VERIFY,data,function (result) {
                        if (result) {
                            YYMessage.info("抱歉，该用户名已被占用。请重新填写!");
                            RegisterUserName = true;
                        } else {
                            RegisterUserName = false;
                        }
                    });
                }
            }else{

            }
        }
    },
    "P005164":{
        onViewWillMount:function(options){
            console.log('page onViewWillMount',options);
            _this = this;
            _this.findUI('card').accordion = true;
        }
        ,onViewDidMount:function(options){
            console.log('page onViewDidMount',options);
            var loginUserPwd = document.getElementById("loginUserPwd");
            if(loginUserPwd != null){
                loginUserPwd.type = 'password';
            }
        }
        ,onViewWillUpdate:function(options){
            console.log('page onViewWillUpdate',options);
        }
        ,onViewDidUpdate:function(options){
            console.log('page onViewDidUpdate',options);
        }
    }
}
var SimplePage = YYClass.create({
    render:function(){
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P005164" uiEvent={EventHanlder}/>
    }
});
module.exports = SimplePage;