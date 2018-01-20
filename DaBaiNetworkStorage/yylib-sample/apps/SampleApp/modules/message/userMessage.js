var React = require('react');
var _ = require('lodash');
var {YYClass} = require('yylib-ui');
var {YYCreatePage} =  require('yylib-business');
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var THIS;
var UserDate;
//页面初始化

/*根据出生日期算出年龄*/
function jsGetAge(strBirthday){
    var returnAge;
    var strBirthdayArr=strBirthday.split("-");
    var birthYear = strBirthdayArr[0];
    var birthMonth = strBirthdayArr[1];
    var birthDay = strBirthdayArr[2];

    var d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();

    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }
    else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth) {
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0){returnAge = ageDiff - 1;}
                else{returnAge = ageDiff ;}
            }else{
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0){returnAge = ageDiff - 1;}
                else{returnAge = ageDiff ;}
            }
        }else{returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    return returnAge;//返回周岁年龄
}
function saveAge(date) {
    var age = THIS.findUI('age');
    age.value = jsGetAge(date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate());
    THIS.refresh();
}
var EventHanlder = {
    "saveMessage":{
        onClick:function () {
            var basicInformation = THIS.findUI('basicInformation');
            basicInformation.api.validateFields(function (errors,values) {
                if(!errors){
                    console.log('提交保存信息',values);
                }
            });
        }
    },
    "alterMessage":{
        onClick:function () {
            var loginFrom = THIS.findUI('loginFrom');
            loginFrom.api.validateFields(function (errors,values) {
                if(!errors){
                    console.log('提交保存信息',values);
                }
            });
        }
    },
    "P005076":{
        onViewWillMount:function(options){
            console.log('page onViewWillMount',options);
            THIS = this;
            UserDate = THIS.getRouteParams();
            var name = THIS.findUI('name');
            name.value = UserDate.name;
            var startTime = THIS.findUI('startTime');
            startTime.defaultValue = UserDate.userStartTime;
            var email = THIS.findUI('email');
            email.value = UserDate.userEmail;
            var userName = THIS.findUI('userName');
            userName.value = UserDate.userName;
            var birthday = THIS.findUI('birthday');
            birthday.onChange = saveAge;
            var userPwd = THIS.findUI('userpwd');
            userPwd.type = 'password';
            var pwd1 = THIS.findUI('pwd1');
            pwd1.type = 'password';
            var pwd2 = THIS.findUI('pwd2');
            pwd2.type = 'password';
        }
        ,onViewDidMount:function(options){
            console.log('page onViewDidMount',options);
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
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P005076" uiEvent={EventHanlder}/>
    }
});
module.exports = SimplePage;