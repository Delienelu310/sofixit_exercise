import { apiClient } from "./ApiClient";

export function monitor(time){
    return apiClient.get("/report/" + time);
}