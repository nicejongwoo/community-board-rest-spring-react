import {atom} from "recoil";

const loggedState = atom({
    key: "loggedState",
    default: false
});

export {loggedState}

