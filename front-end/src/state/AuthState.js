import {atom} from "recoil";

export const loggedState = atom({
    key: "loggedState",
    default: false
});

export const tokenState = atom({
    key: "tokenState",
    default: sessionStorage.getItem("token"),
});

export const accountState = atom({
    key: "accountState",
    default: sessionStorage.getItem("account"),
});


