import axios from "axios";

export const BASE_URL = "http://localhost:8080";

//will be used to call sesver
export const myAxios = axios.create({
  baseURL: BASE_URL,
});
