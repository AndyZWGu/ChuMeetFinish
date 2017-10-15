

$(document).ready(function() {
    //toggle `popup` / `inline` mode
    $.fn.editable.defaults.mode = 'popup';     
    
$('#actName').editable({
	    type: 'text',
		name: 'actName',
	    url: '/post',
});
$('#actPriID').editable();
$('#actStartDate').editable();
$('#actEndDate').editable();
$('#actSignStartDate').editable();
$('#actSignEndDate').editable();
$('#actTimeTypeID').editable();
$('#actTimeTypeCnt').editable();
$('#actMemMax').editable();
$('#actMemMin').editable();
$('#actImg').editable();
$('#actContent').editable();
$('#actAdr').editable();

    
    //make status editable
    $('#status').editable({
        type: 'select',
        title: 'Select status',
        placement: 'right',
        value: 2,
        source: [
            {value: 1, text: 'status 1'},
            {value: 2, text: 'status 2'},
            {value: 3, text: 'status 3'}
        ]
        /*
        //uncomment these lines to send data on server
        ,pk: 1
        ,url: '/post'
        */
    });
});