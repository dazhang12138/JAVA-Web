var React = require('react');
var _ = require('lodash');
var {YYCreatePage} =  require('yylib-business');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var React = require('react');
var { YYClass,YYPage,YYCard,YYForm,YYInput,YYMessage,YYButton,YYFormItem,YYTabs,YYTab} = require('yylib-ui');
require('./css/login.css');
var ajax = require('yylib-utils/ajax');
var URL = require('./Url');

var THIS;

const formItemLayout = {
    labelCol: { span: 7 },
    wrapperCol: { span: 12 },
};

function noop() {
    return false;
}

var Login = YYClass.create({
    /*登录button提交表单*/
    loginSubmit: function (e) {
        e.preventDefault();
        this.props.form.validateFields(function(errors, values){
            if (values.userNameL == null || values.passwordL == null) {
                console.log('Errors in form!!!');
                YYMessage.error('请填写登录信息!');
                return;
            };
            var data = {
                userName : values.userNameL,
                userPwd : values.passwordL
            };
            /*Ajax后台验证登录*/
            ajax.postJSON(URL.LOGIN,data,function (user) {
                var result = user.result;
                if(result == 'error'){
                    YYMessage.error('用户名或密码不正确！请重新输入或找回密码!');
                }else{
                    THIS.routeTo("DB/mainPage",null,user);
                }
            });
        });
    },
    /*注册表单昵称框校验*/
    nameExists: function (rule, value, callback) {
        if (!value) {
            callback();
        } else {
            setTimeout(() => {
                var data = {
                    name:value
                };
                ajax.getJSON(URL.VERIFY,data,function (result) {
                    if (result) {
                        callback([new Error('抱歉，该昵称已被占用。')]);
                    } else {
                        callback();
                    }
                });
            }, 800);
        }
    },
    /*注册表单登录名框校验*/
    userNameExists:function (rule, value, callback) {
        if (!value) {
            callback();
        } else {
            setTimeout(() => {
                var data = {
                    userName:value
                };
                ajax.getJSON(URL.VERIFY,data,function (result) {
                    if (result) {
                        callback([new Error('抱歉，该登录名已被占用。')]);
                    } else {
                        callback();
                    }
                });
            }, 800);
        }
    },
    /*注册button提交表单*/
    registerSubmit:function(e){
        e.preventDefault();
        this.props.form.validateFields(function (errors, values) {
            if (!!errors) {
                console.log('Errors in form!!!');
                YYMessage.error('请正确填写注册信息!');
                return;
            }
            console.log('Submit!!!');
            console.log(values);
            var data = {
                userName : values.userName,
                userPwd : values.passwd,
                name:values.name,
                userEmail:values.email
            };
            console.log(data);
            /*ajax后台保存数据*/
            ajax.postJSON(URL.REGISTER,data,function (result) {
                if(result){
                    YYMessage.success('注册成功！');
                }else{
                    YYMessage.error('注册失败!');
                }
            });
        });
    },
    /*注册表单清空*/
    handleReset: function (e) {
        e.preventDefault();
        this.props.form.resetFields();
    },
    /*密码验证*/
    checkPass(rule, value, callback) {
        const { validateFields } = this.props.form;
        if (value) {
            validateFields(['rePasswd'], { force: true });
        }
        callback();
    },
    /*二次密码验证*/
    checkPass2(rule, value, callback) {
        const { getFieldValue } = this.props.form;
        if (value && value !== getFieldValue('passwd')) {
            callback('两次输入密码不一致！');
        } else {
            callback();
        }
    },
    render: function() {
        var { getFieldProps, isFieldValidating, getFieldError } = this.props.form;
        /*昵称校验*/
        const nameProps = getFieldProps('name', {
            rules: [
                { required: true, min: 2, message: '昵称至少为2个英文字符或两个汉字' },
                { validator: this.nameExists },
            ],
        });
        /*登录名校验*/
        const UserNameProps = getFieldProps('userName', {
            rules: [
                { required: true, min: 6, message: '登录用户名至少为6个字符' },
                { validator: this.userNameExists },
            ],
        });
        /*密码校验*/
        const passwdProps = getFieldProps('passwd', {
            rules: [
                { required: true, min: 6, whitespace: true, message: '请填写密码' },
                { validator: this.checkPass },
            ],
        });
        /*二次密码校验*/
        const rePasswdProps = getFieldProps('rePasswd', {
            rules: [{
                required: true,
                whitespace: true,
                message: '请再次输入密码',
            }, {
                validator: this.checkPass2,
            }],
        });
        /*邮箱校验*/
        const emailProps = getFieldProps('email', {
            validate: [
                //删掉点击显示Email is require的提示
                // {
                //     rules: [
                //         { required: true },
                //     ],
                //     trigger: 'onBlur',
                // },
                {
                    rules: [
                        { type: 'email', message: '请输入正确的邮箱地址' },
                    ],
                    trigger: ['onBlur','onChange'],
                }],
        });
        return (
            <YYPage >
                <YYTabs defaultActiveKey="1">
                    <YYTab key="10"/>
                    <YYTab key="11"/>
                    <YYTab key="12"/>
                    <YYTab key="13"/>
                    <YYTab key="14"/>
                    <YYTab key="15"/>
                    <YYTab key="16"/>
                    <YYTab key="17"/>
                    <YYTab key="18"/>
                    <YYTab key="19"/>
                    <YYTab tab="已有账户,点击登录" key="1">
                        <YYCard title="登录" bordered={false} style={{width: 500}}>
                            <YYForm horizontal>
                                <YYFormItem {...formItemLayout} label="登录名" hasFeedback>
                                    <YYInput  placeholder="请输入账户名" {...getFieldProps('userNameL')} />
                                </YYFormItem>
                                <YYFormItem {...formItemLayout} label="密码" hasFeedback>
                                    <YYInput  type="password" placeholder="请输入密码" {...getFieldProps('passwordL')}/>
                                </YYFormItem>
                                {/*<YYFormItem >*/}
                                {/*<YYCheckbox {...getFieldProps('agreement')}>记住我</YYCheckbox>*/}
                                {/*</YYFormItem>*/}
                                <YYFormItem wrapperCol={{ span: 12, offset: 7 }}>
                                    <YYButton type="primary" onClick={this.loginSubmit}>登陆</YYButton>
                                    &nbsp;&nbsp;&nbsp;
                                    <YYButton type="ghost" onClick={this.handleReset}>重置</YYButton>
                                </YYFormItem>
                            </YYForm>
                        </YYCard>
                    </YYTab>
                    <YYTab tab="没有账户,点击注册" key="2">
                        <YYCard title="注册" bordered={false} style={{width: 500}}>
                            <YYForm horizontal>
                                <YYFormItem {...formItemLayout} label="昵称" hasFeedback help={isFieldValidating('name') ? '校验中...' : (getFieldError('name') || []).join(', ')}>
                                    <YYInput {...nameProps} placeholder="请输入昵称" />
                                </YYFormItem>
                                <YYFormItem {...formItemLayout} label="登录名" hasFeedback help={isFieldValidating('userName') ? '校验中...' : (getFieldError('userName') || []).join(', ')}>
                                    <YYInput {...UserNameProps} placeholder="请输入登录名" />
                                </YYFormItem>
                                <YYFormItem {...formItemLayout} label="密码" hasFeedback>
                                    <YYInput {...passwdProps} type="password" autoComplete="off" onContextMenu={noop} onPaste={noop} onCopy={noop} onCut={noop}/>
                                </YYFormItem>
                                <YYFormItem {...formItemLayout} label="确认密码" hasFeedback>
                                    <YYInput {...rePasswdProps} type="password" autoComplete="off" placeholder="两次输入密码保持一致" onContextMenu={noop} onPaste={noop} onCopy={noop} onCut={noop}/>
                                </YYFormItem>
                                <YYFormItem {...formItemLayout} label="邮箱" hasFeedback>
                                    <YYInput {...emailProps} type="email" placeholder="你输入注册邮箱。用来找回密码" />
                                </YYFormItem>
                                <YYFormItem wrapperCol={{ span: 12, offset: 7 }}>
                                    <YYButton type="primary" onClick={this.registerSubmit}>注册</YYButton>
                                    &nbsp;&nbsp;&nbsp;
                                    <YYButton type="ghost" onClick={this.handleReset}>重置</YYButton>
                                </YYFormItem>
                            </YYForm>
                        </YYCard>
                    </YYTab>
                    <YYTab tab="忘记密码,找回密码" key="3"></YYTab>
                </YYTabs>
            </YYPage>
        )
    }
});

Login = YYForm.create()(Login);

var MyParser = {};
MyParser.Login = Login;


//页面初始化
var EventHanlder = {
    "P004723":{
        onViewWillMount:function(options){
            console.log('page onViewWillMount',options);
        }
        ,onViewDidMount:function(options){
            console.log('page onViewDidMount',options);
            THIS = this;
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
        return <YYCreatePage {...this.props} appCode="A000776" pageCode="P004723" uiEvent={EventHanlder}
                             uiParser={MyParser}/>
    }
});
module.exports = SimplePage;
