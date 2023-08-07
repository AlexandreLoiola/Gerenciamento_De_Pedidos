import React from "react";
import { Button, Table } from "react-bootstrap";
import TableToggle from "./TableToggle";
import { PencilIcon, ThrashIcon } from "./styles";


interface DataType {
  [key: string]: any;
}

interface IProps {
  columnTitles: string[];
  data: DataType[];
  objectKeys: string[];

  handleDelete: (index: number) => void;
  navigateToUpdateForm: () => void;
}

const ManagementTable: React.FC<IProps> = ({columnTitles, data, objectKeys, handleDelete, navigateToUpdateForm}) => {

  return (
    <Table striped bordered hover>
      <thead>
        <tr>
          {columnTitles.map((column) => ( 
            <th key={column}>{column}</th> 
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((data, index) => (
          <tr key={index}>
            <td>{index + 1}</td>
            {objectKeys.map((key) => (
              <td key={key}>
                {typeof data[key] === 'boolean' ? (
                  <TableToggle onToggle={() => true} initialValue={data[key]}/>
                ) : (
                  data[key]
                )}
              </td>
            ))}
            <td>
              <Button variant="warning" style={{ margin: "0 6px" }} onClick={navigateToUpdateForm}>
                <PencilIcon />
              </Button>
              <Button variant="danger" style={{ margin: "0 6px" }} onClick={() => handleDelete(index)}>
                <ThrashIcon />
              </Button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default ManagementTable;