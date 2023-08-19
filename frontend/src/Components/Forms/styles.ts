import styled from "styled-components";
import { Form } from "react-bootstrap";
import { HiMagnifyingGlass } from "react-icons/hi2";

export const StyledFormGroup = styled(Form.Group)`
  margin-bottom: 1.5rem;
`;

export const StyledFormLabel = styled(Form.Label)`
  font-size: 1.2rem;
  color: #333;
`;

export const StyledFormControl = styled(Form.Control)`
  font-size: 1rem;
  background-color: white;
  padding: 0.5rem 1rem;
  border: 1px solid lightgray;
  width: 500px;
`;

export const StyledFormText = styled(Form.Text)`
  font-size: 0.8rem;
  color: #777;
`;

export const StyledFormControlReadonly = styled(Form.Control)`
  border: 1px solid #ccc;
  font-size: 1rem;
  background-color: #d3d3d3;
  width: 164px;
  padding: 0.5rem 1rem;
  cursor: not-allowed;

  &:hover {
    background-color: #a9a9a9;
  }
`;

export const MagnifyingGlassIcon = styled(HiMagnifyingGlass)`
  color: white;
  font-size: 26px;
  font-weight: 700;
`;