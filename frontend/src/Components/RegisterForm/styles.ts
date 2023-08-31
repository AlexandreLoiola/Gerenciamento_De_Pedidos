import styled from "styled-components";

export const FormContainer = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;

  margin: -30PX 0 auto;
  display: grid;
  gap: 16px;
`;

export const FormRow = styled.div`
  display: grid;
  gap: 50px;
  grid-template-columns: repeat(2, 1fr);
`;
