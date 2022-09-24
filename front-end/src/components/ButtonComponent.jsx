import React from "react";
import styled from "styled-components";

const StyledFlexRoot = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-wrap: wrap;
`

const StyledButtonWrapper = styled(StyledFlexRoot)`
  width: 100%;
  justify-content: end;
  text-align: right;
  margin-bottom: 1em;
  li {
    margin: 0 8px;
    &:last-child {
      margin: 0;
    }
  }
`

const StyledButton = styled.button`
  background-color: transparent;
  padding: .6em;
  border-radius: 5px;
  cursor: pointer;
  border: 1px solid rgb(170, 170, 170);
  min-width: 40px;
  &:hover {
    background-color: rgb(170, 170, 170);
    color: #ffffff;    
  }
`

export const AddButton = ({moveInsertPage}) => {
    return (
        <StyledFlexRoot className="flex-root">
            <StyledButtonWrapper as="ul">
                <li>
                    <StyledButton
                        type="button"
                        onClick={moveInsertPage}
                    >
                        등록
                    </StyledButton>
                </li>
            </StyledButtonWrapper>
        </StyledFlexRoot>
    );
};