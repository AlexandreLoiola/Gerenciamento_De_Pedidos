import styled from "styled-components";

export const FormContainer = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;

  margin: 0 auto;
  display: grid;
  gap: 16px;
`;

export const FormInput = styled.input`
display: flex;
    margin: 0 auto;
    width: 450px;
    padding: 0 30px;
    height: 60px;
    border-radius: 50px;
    margin-bottom: 20px;
    outline: none;
    box-sizing: border-box;
    cursor: text;

    font-weight: regular;
    font-size: 18px;

    &:focus {
    border: 3px solid #3D9F2A;
  }
`;

export const FormRow = styled.div`
  display: grid;
  gap: 16px;
  grid-template-columns: repeat(2, 1fr);
`;
