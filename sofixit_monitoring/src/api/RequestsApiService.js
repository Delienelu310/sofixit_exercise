import { apiClient } from "./ApiClient";


export function executeRequest(url, format, size){
    return apiClient.get(url + size + "?format=" + format);
}