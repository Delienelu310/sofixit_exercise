import { apiClient } from "./ApiClient";

export function monitor(time){
    apiClient.get("/report/" + time);
}