import React, { useState } from "react";
import { ToggleContainer, ToggleCircleOn, ToggleCircleOff } from "./styles";

interface Props {
  onToggle: (value: boolean) => void;
  initialValue: boolean;
}

const TableToggle: React.FC<Props> = ({ onToggle, initialValue }) => {
  const [isOn, setIsOn] = useState(initialValue);

  const handleToggle = () => {
    setIsOn(!isOn);
    onToggle(!isOn);
  };

  return (
    <ToggleContainer onClick={handleToggle} isOn={isOn}>
      {isOn ? <ToggleCircleOn /> : <ToggleCircleOff />}
    </ToggleContainer>
  );
};

export default TableToggle;
