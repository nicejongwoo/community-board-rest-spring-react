import React from "react";

export const AddButton = ({moveInsertPage}) => {
    return (
        <div className="flex-root">
            <ul className="button-wrapper button-right">
                <li>
                    <button
                        type="button"
                        className="add-button"
                        onClick={moveInsertPage}
                    >
                        등록
                    </button>
                </li>
            </ul>
        </div>
    );
};