import {atom} from "recoil";

export const loggedState = atom({
    key: "loggedState",
    default: JSON.parse(sessionStorage.getItem("account")) !== null,
});

export const tokenState = atom({
    key: "tokenState",
    default: JSON.parse(sessionStorage.getItem("token")),
});

export const accountState = atom({
    key: "accountState",
    default: JSON.parse(sessionStorage.getItem("account")),
});


