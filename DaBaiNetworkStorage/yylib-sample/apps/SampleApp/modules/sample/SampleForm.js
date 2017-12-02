/**
 * Created by wuhao on 2016/11/7.
 */
var React = require('react');
var {YYClass,YYPage,YYToolbar,YYCol,YYButton,YYAccordion,YYRadioGroup,YYRadio,YYSelect,YYOption,YYSwitch,YYDatePicker,
    YYAccordions,YYForm,YYInput, YYFormItem,YYMessage,YYBreadcrumb,YYInputNumber,YYRate, YYEditTable,YYIcon} = require('yylib-ui');
var _ = require('lodash');
var {Link} = require('react-router')
var ajax = require('yylib-utils/ajax');
var ReduxUtils = require('yylib-utils/ReduxUtils');
var {URL_HOME,MODULE_URL} = require('../../actions/RestUrl');
var SampleDetailTable = require('./SampleDetailTable');

var mapStateToProps = function (state) {
    return {
        detailTableData:state.sample.detailTableData
    }
}

var SampleForm = YYClass.create({
    _handleSubmit:function () {
        var self = this
        this.props.form.validateFields((errors,values) => {
            if (!!errors) {
                // console.log('errors', errors)
                return;
            } else {
                values.id = this.props.location.query.id
                _.assign(values, {detailTableData:this.props.detailTableData})
                ajax.postJSON(MODULE_URL.SAMPLE, values, function (result) {
                    if (result.success) {
                        YYMessage.success('保存成功')
                        self.props.router.push('/sample')
                    } else {
                        YYMessage.error(result.backMsg)
                    }
                })
            }
        })
    },
    render:function () {
        const { getFieldProps } = this.props.form;
        const formItemLayout = {
            labelCol: { span: 5 },
            wrapperCol: { span: 12 },
        };
        var breadcrumbItems = [
            {title:'主页',href:URL_HOME},
            {title:'示例一',href:'sample'},
            {title:'表单示例'}
        ]

        var { query } = this.props.location
        return (

            <YYPage>
                <YYBreadcrumb itemsData={breadcrumbItems}/>
                <YYToolbar>
                    <YYButton type="primary" onClick={this._handleSubmit}>提交</YYButton>
                    <Link to="sample"><YYButton>取消</YYButton></Link>
                </YYToolbar>
                <YYAccordions>
                    <YYAccordion key="panel1" header="基本信息" isOpen={true}>
                        <YYForm horizontal>
                            {(query && query.id)?
                                (<YYCol span="12">
                                    <YYFormItem label="id" {...formItemLayout}>
                                        <YYInput readOnly {...getFieldProps('id', {initialValue:query.id})} />
                                    </YYFormItem>
                                </YYCol>):null}
                            <YYCol span="12"><YYFormItem label="学号" {...formItemLayout}>
                                <YYInput  placeholder="请输入内容" {...getFieldProps('code', {initialValue:query.code})} />
                            </YYFormItem></YYCol>
                            <YYCol span="12"><YYFormItem label="姓名" {...formItemLayout}>
                                <YYInput  placeholder="请输入内容" {...getFieldProps('name', {initialValue:query.name})} />
                            </YYFormItem></YYCol>
                            <YYCol span="12"><YYFormItem label="性别" {...formItemLayout}>
                                <YYRadioGroup defaultValue="1" onChange={this.onChange} value={query.gender}
                                              {...getFieldProps('gender', {initialValue:query.gender})}>
                                    <YYRadio key="1" value="1">男</YYRadio>
                                    <YYRadio key="2" value="2">女</YYRadio>
                                    <YYRadio key="3" value="0">保密</YYRadio>
                                </YYRadioGroup>
                            </YYFormItem></YYCol>
                            <YYCol span="12"><YYFormItem label="名次" {...formItemLayout}>
                                <YYInputNumber  placeholder="请输入名次" {...getFieldProps('ranking', {initialValue:query.ranking})} />
                            </YYFormItem></YYCol>
                            <YYCol span="12"><YYFormItem label="专业" {...formItemLayout}>
                                <YYSelect style={{ width: 200 }} {...getFieldProps('major', {initialValue:query.major})}>
                                    <YYOption value="Food Science and Engineering">食品科学与工程专业</YYOption>
                                    <YYOption value="Chemistry Engineering and Technology">生物工程专业</YYOption>
                                    <YYOption value="Pharmaceutical Engineering" disabled>制药工程专业</YYOption>
                                    <YYOption value="Management Engineering Department">管理系</YYOption>
                                </YYSelect>
                            </YYFormItem></YYCol>
                            <YYCol span="12"><YYFormItem label="评价" {...formItemLayout}>
                                <YYRate allowHalf defaultValue={2.5} {...getFieldProps('evaluate', {initialValue:query.evaluate})}/>
                            </YYFormItem></YYCol>
                            <YYCol span="12"><YYFormItem label="管理员" {...formItemLayout}>
                                <YYSwitch {...getFieldProps('admin', {valuePropName:'checked',initialValue:query.admin })} />
                            </YYFormItem></YYCol>
                            <YYCol span="12"><YYFormItem label="创建日期" {...formItemLayout}>
                                <YYDatePicker  placeholder="请输入内容" {...getFieldProps('modifyDate', {initialValue:query.modifyDate})} />
                            </YYFormItem></YYCol>
                        </YYForm>
                    </YYAccordion>
                    <YYAccordion key="panel2" header="详细信息"  isOpen={true}>
                        <SampleDetailTable id={query.id} detailTableData={this.props.detailTableData} sendAction={this.props.sendAction}/>
                    </YYAccordion>
                </YYAccordions>

            </YYPage>
        )
    }
})

SampleForm = YYForm.create()(SampleForm)
module.exports = ReduxUtils.connect(SampleForm, mapStateToProps)