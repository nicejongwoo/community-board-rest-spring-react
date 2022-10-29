import React from "react";
import styled from "styled-components";

const StyledTableWrapper = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  padding: 0;
  margin-bottom: 1em;
  background-color: #ffffff;
  box-shadow: 0px 2px 1px -1px rgb(0 0 0 / 20%), 0px 1px 1px 0px rgb(0 0 0 / 14%), 0px 1px 3px 0px rgb(0 0 0 / 12%);
`

const StyledTable = styled.table`
  width: 100%;
  display: table;
  border-spacing: 0;
  border-collapse: collapse;

  thead {
    font-size: 14px;

    tr {
      color: #ffffff;
      background-color: #1f2937;
      display: table-row;
      vertical-align: middle;
      outline: 0;
      //border-top: 2px solid #cccccc;
      //border-bottom: 2px solid #cccccc;
      th {
        text-align: center;
        font-weight: 600;
        line-height: 1rem;
        display: table-cell;
        padding: 16px;
        //border-bottom: 1px solid rgba(244, 244, 244, 1);
        vertical-align: inherit;
      }
    }
  }

  tbody {
    font-size: 14px;

    tr {
      cursor: pointer;
      color: inherit;
      outline: 0;
      vertical-align: middle;
      font-weight: 600;

      &:hover {
        //background-color: #393939;
        background-color: #374151;
        color: #ffffff;
        //background-color: #dce2f9;
        //background-color: #e1e1e1;
      }
    }

    td {
      display: table-cell;
      padding: 16px;
      font-size: 0.875rem;
      font-weight: 400;
      line-height: 1;
      border-bottom: 1px solid rgba(224, 224, 224, 1);
      vertical-align: inherit;
      text-align: center;
    }
  }

`

export const TableComponent = ({columnNames, renderContents}) => {

    return (
        <StyledTableWrapper className="content-item flex-root table-wrapper">
            <StyledTable className="">
                <colgroup>
                    <col width="7%"/>
                </colgroup>
                <thead>
                <tr>
                    {columnNames && columnNames.map((element, index) =>
                        <th
                            key={index}
                        >
                            {element}
                        </th>
                    )}
                </tr>
                </thead>
                <tbody>
                {renderContents}
                </tbody>
            </StyledTable>
        </StyledTableWrapper>
    );
}