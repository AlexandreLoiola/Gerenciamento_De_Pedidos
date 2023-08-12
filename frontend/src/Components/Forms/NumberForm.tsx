import React, { useState } from "react";
import { StyledFormGroup } from "./styles";
import { Form } from "react-bootstrap";

interface IProps {
  label: string;
  placeholder: string;
  value?: number;
  min?: number;
  max?: number;
  step?: number;
  onChange: (value: number) => void;
}

const NumberForm: React.FC<IProps> = ({
  label,
  placeholder,
  value,
  min,
  max,
  step,
  onChange,
}) => {
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    onChange(Number(event.target.value));
  };

  return (
    <Form.Group controlId="formBasicNumber">
      <Form.Label>{label}</Form.Label>
      <Form.Control
        type="text"
        placeholder={placeholder}
        min={min}
        max={max}
        value={value}
        step={step}
        onChange={handleChange}
      />
    </Form.Group>
  );
};

export default NumberForm;
