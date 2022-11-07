import {atom} from "recoil";

export const authState = atom({
    key: "authState",
    default: JSON.parse(localStorage.getItem("auth")),
});