/**
 * Created by wuhao on 2016/11/7.
 */
var assign = require('lodash/assign');

var actions = {
    "loadTable":function(prevState,tableData){
        return assign({},prevState,{tableData:tableData});
    },
    "loadDetailTable":function (prevState,detailTableData) {
        return assign({},prevState,{detailTableData:detailTableData});
    }
}

module.exports = actions;