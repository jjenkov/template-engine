# Template Engine
A minimal template engine capable of merging a template with data to be inserted into the template at specific 
insertion points. 

Templates are binary data blocks. Data that can be inserted are also binary data blocks. 
Thus, this template engine can be used for both text and binary files (in theory at least).

## Use Cases
This template engine can be used to merge a website HTML template with content from different pages
to generate finished, full HTML pages. Create an HTML template file, insert some insertion points
and merge it with content from different content files. The content files should of course only contain
the main content to be displayed in the HTML page. The outer layout should be provided by the 
HTML template file.


## Core Classes

|Class|Description|
|-----|-----------|
|Utf8TemplateParser| Can parse a UTF-8 text (bytes) and create a Template from it. |
|Template| Template with insertion points. |
|InsertionPoint| An area in a template at which data can be inserted during a merge operation.|
|DataProvider| Provider of data to be inserted into a template during a merge operation. |
|Data| Data returned by a DataProvider. |
|Merger| An operation capable of merging a template with data provided by a DataProvider.|
 
 