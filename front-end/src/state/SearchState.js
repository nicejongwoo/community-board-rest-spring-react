import {atom} from "recoil";

const searchValueState = atom({
    key: "searchValuesState",
    default: {
        "type": "",
        "keyword": "",
    },
});

const currentPageState = atom({
    key: "currentPageState",
    default: 0
});

const totalElementState = atom({
    key: "totalElementState",
    default: 0,
});

const sizeState = atom({
    key: "sizeState",
    default: 10,
});

const sortState = atom({
    key: "sortState",
    default: "id%2Cdesc",
});

export {
    searchValueState,
    currentPageState,
    totalElementState,
    sizeState,
    sortState
}