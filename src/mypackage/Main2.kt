package mypackage

// Defines an interface for all elements that can be rendered.
// Classes implementing this interface must provide their own `render` method.
interface Element {
    fun render(): String
}

// Abstract class representing a paragraph element.
// It implements the `Element` interface and stores the text to be rendered.
// Subclasses will define how the text is formatted.
abstract class Paragraph(protected val text: String): Element

// Concrete implementation of a paragraph for HTML documents.
// Inherits the text property from `Paragraph` and formats it as an HTML paragraph.
class HtmlParagraph(text: String): Paragraph(text){
    override fun render() = "<p>$text</p>" // Converts the text to an HTML paragraph.
}

// Concrete implementation of a paragraph for Markdown documents.
// Inherits the text property from `Paragraph` and formats it as a Markdown paragraph.
class MarkdownParagraph(text: String): Paragraph(text){
    override fun render() = "$text\n" // Converts the text to a Markdown paragraph.
}

// Abstract class for documents containing multiple elements.
// Implements logic for managing and rendering elements but delegates the creation
// of specific paragraph types to its subclasses using the Factory Method.
abstract class Document() {
    // A list to store the elements of the document.
    private val elements = mutableListOf<Element>()

    // Abstract Factory Method for creating paragraphs.
    // Subclasses will implement this method to create specific types of paragraphs.
    protected abstract fun createParagraph(text: String): Paragraph

    // Adds a paragraph to the document.
    // Relies on the Factory Method `createParagraph` to determine the paragraph type.
    fun addParagraph(text: String) = elements.add(createParagraph(text))

    // Renders all the elements in the document by calling their `render` methods.
    // Joins the rendered elements into a single string separated by newlines.
    fun render() = elements.joinToString("\n") { it.render() }
}

// Concrete implementation of a document for HTML.
// Overrides the Factory Method to create `HtmlParagraph` instances.
class HtmlDocument : Document(){
    override fun createParagraph(text: String) = HtmlParagraph(text) // Creates an HTML paragraph.
}

// Concrete implementation of a document for Markdown.
// Overrides the Factory Method to create `MarkdownParagraph` instances.
class MarkdownDocument : Document(){
    override fun createParagraph(text: String) = MarkdownParagraph(text) // Creates a Markdown paragraph.
}

// Main function demonstrating the use of the Factory Method pattern.
fun main() {
    // Creates an HtmlDocument instance (the factory for HTML paragraphs).
    // To generate a Markdown document, switch this to MarkdownDocument.
    val doc = HtmlDocument()
    
    // Adds a paragraph to the document.
    // The specific type of paragraph (HTML or Markdown) is determined by the factory method.
    doc.addParagraph("This is the first paragraph.")
    
    // Renders the document and prints the output.
    // The format of the output depends on the specific document type (HTML or Markdown).
    println(doc.render())
}
