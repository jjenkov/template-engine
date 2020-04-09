# Template Engine
A minimal template engine capable of merging a template with data to be inserted into the template at specific 
insertion points. 

Templates are binary data blocks. Data that can be inserted are also binary data blocks. 
Thus, this template engine can be used for both text and binary files (in theory at least).


## Key Classes

|Class|Description|
|-----|-----------|
|Template| Template with insertion points. |
|DataProvider| Provider for data to be inserted into a template. |
|Data| Data returned by a DataProvider. |
|Merger| An operation capable of merging a template with data provided by a DataProvider.|
 
 