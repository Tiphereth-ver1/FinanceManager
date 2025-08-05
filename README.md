> 9:58PM, July 5, 2025 
> *Me being a dumbass*: I should build an app using Java.

And that's what I did.

# Finance Manager
This program is a rudimentary finance manager which has a few basic features so far. Bocchi-styled for no particular reason, I just wanted to practice programming in Java a little more and learning a mix of backend and frontend development as well as general good coding practices.

<img width="882" height="639" alt="image" src="https://github.com/user-attachments/assets/750ccacb-84c8-4089-a86e-3f434d048c99" />

## Main features:

A transaction table. Data is stored using a csv. Contains data values for date (date of transaction), type (currently contains only DEPOSIT and EXPENDITURE as selections), value (the value of the transaction), and description. Due to the structure of the application using .cv files, commas will break the description. However, everything else should function normally. The checkboxes along the left side of the table can be used to delete transactions with the transaction button.
<img width="876" height="149" alt="image" src="https://github.com/user-attachments/assets/ab7e99fb-71a8-4799-93ea-78c7079a2a1d" />

The Add button and delete button. The add button will bring up the following menu, which can be used to add transactions to the transaction table. The same information contained in the transaction table needs to be filled out for this particular prompt. The Delete button will delete any items selected using the checkbox in the transaction table.

<img width="579" height="54" alt="image" src="https://github.com/user-attachments/assets/8af8d152-8462-4b2a-8b39-a25897bf6549" />

<img width="577" height="175" alt="image" src="https://github.com/user-attachments/assets/71d74a24-a296-4236-aefe-d25cb5cfcd5f" />

Transactions can also be sorted based on the date or value of the entry. Future implementations will contain filtering options as well. The update button will apply any sorting parameters set here.

<img width="374" height="118" alt="image" src="https://github.com/user-attachments/assets/680b3881-f6de-4495-8760-df8d1fcf8dcc" />

