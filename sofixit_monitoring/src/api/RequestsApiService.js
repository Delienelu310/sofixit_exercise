import axios from "axios";


export function executeRequest(url, format, size){
    return axios.get(url + size + "?format=" + format);
}