import {atom} from "recoil";

const loggedState = atom({
    key: "loggedState",
    default: true
});

export {loggedState}

