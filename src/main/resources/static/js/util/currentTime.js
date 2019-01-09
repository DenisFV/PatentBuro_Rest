
export function getCurrentTime() {
    var time = new Date();
    var res = time.getFullYear()+'-'+(time.getMonth()+1)+'-'+time.getDate()+' '+
    time.getHours()+':'+time.getMinutes()+':'+time.getSeconds()+'.'+time.getMilliseconds();

    return res;
}