var timeArray = [
    "00:00", "04:00", "08:00",
    "12:00", "16:00",
    "20:00", "23:59"
];

function scale(){
    var now = moment().format("HH:mm");
    var timeScale = [];
    timeArray.forEach(function(e, i, arr){
        if(now > e){
            timeScale = arr.slice(i, arr.length);
            return;
        }
    });
    return timeScale;
}

function padzero(str, length){
    return ("0" + str).substr(-length);
}

function timeSlideBar(clazz){

    var timeScale = scale();
    var from = parseInt(timeScale[0].substring(0, timeScale[0].indexOf(":"))) * 60;

    var to = parseInt(timeScale[timeScale.length - 1].substring(0, timeScale[0].indexOf(":"))) * 60
        + parseInt(timeScale[timeScale.length - 1].substring(timeScale[0].indexOf(":") + 1, timeScale[0].length));

    $(clazz).jRange({
        from: from,
        to: to,
        step: 30,
        scale: timeScale,
        format: function(value){
            if(value !== 60 * 24){
                return padzero(parseInt(value / 60), 2) + ":" + padzero(value % 60, 2);
            }
            return padzero(parseInt((value - 1) / 60), 2) + ":" + padzero((value - 1) % 60, 2);
        },
        width: 332,
        height: 50,
        showLabels: true,
        snap: true
    });
    $(clazz).jRange("setValue", "" + to);

}
