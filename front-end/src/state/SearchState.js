import {atom} from "recoil";

export const searchValueState = atom({
    key: "searchValuesState",
    default: {
        "type": "",
        "keyword": "",
    },
});

export const currentPageState = atom({
    key: "currentPageState",
    default: 0
});

export const totalElementState = atom({
    key: "totalElementState",
    default: 0,
});

export const sizeState = atom({
    key: "sizeState",
    default: 10,
});

export const sortState = atom({
    key: "sortState",
    default: "id%2Cdesc",
});

export const typeOptionsState = atom({
    key: "typeOptionsState",
    default: [],
});

export const selectConditionsState = atom({
    key: "selectConditionsState",
    default: [],
})

