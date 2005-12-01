package nextapp.echo2.extras.webcontainer;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.FillImageBorder;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.ImageReference;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Pane;
import nextapp.echo2.app.update.ServerComponentUpdate;
import nextapp.echo2.extras.app.BorderPane;
import nextapp.echo2.webcontainer.ComponentSynchronizePeer;
import nextapp.echo2.webcontainer.ContainerInstance;
import nextapp.echo2.webcontainer.DomUpdateSupport;
import nextapp.echo2.webcontainer.RenderContext;
import nextapp.echo2.webcontainer.SynchronizePeerFactory;
import nextapp.echo2.webcontainer.image.ImageRenderSupport;
import nextapp.echo2.webcontainer.propertyrender.ColorRender;
import nextapp.echo2.webcontainer.propertyrender.ExtentRender;
import nextapp.echo2.webcontainer.propertyrender.FillImageRender;
import nextapp.echo2.webcontainer.propertyrender.FontRender;
import nextapp.echo2.webcontainer.propertyrender.InsetsRender;
import nextapp.echo2.webrender.ClientProperties;
import nextapp.echo2.webrender.ServerMessage;
import nextapp.echo2.webrender.output.CssStyle;
import nextapp.echo2.webrender.servermessage.DomUpdate;

/**
 * <code>ComponentSynchronizePeer</code> implementation for the 
 * <code>BorderPane</code> component.
 */
public class BorderPanePeer 
implements ComponentSynchronizePeer, DomUpdateSupport, ImageRenderSupport {

    private static final String IMAGE_ID_BORDER_TOP_LEFT = "borderTopLeft";
    private static final String IMAGE_ID_BORDER_TOP = "borderTop";
    private static final String IMAGE_ID_BORDER_TOP_RIGHT = "borderTopRight";
    private static final String IMAGE_ID_BORDER_LEFT = "borderLeft";
    private static final String IMAGE_ID_BORDER_RIGHT = "borderRight";
    private static final String IMAGE_ID_BORDER_BOTTOM_LEFT = "borderBottomLeft";
    private static final String IMAGE_ID_BORDER_BOTTOM = "borderBottom";
    private static final String IMAGE_ID_BORDER_BOTTOM_RIGHT = "borderBottomRight";

    private static final Insets DEFAULT_CONTENT_INSETS = new Insets(3);
    private static final FillImageBorder DEFAULT_BORDER 
            = new FillImageBorder(new Color(0x00007f), new Insets(20), DEFAULT_CONTENT_INSETS);

    /**
     * @see nextapp.echo2.webcontainer.ComponentSynchronizePeer#getContainerId(nextapp.echo2.app.Component)
     */
    public String getContainerId(Component child) {
        return ContainerInstance.getElementId(child.getParent()) + "_content";
    }

    /**
     * @see nextapp.echo2.webcontainer.image.ImageRenderSupport#getImage(nextapp.echo2.app.Component,
     *      java.lang.String)
     */
    public ImageReference getImage(Component component, String imageId) {
        if (IMAGE_ID_BORDER_TOP_LEFT.equals(imageId)) {
            FillImageBorder fillImageBorder = ((FillImageBorder) component.getRenderProperty(BorderPane.PROPERTY_BORDER));
            FillImage fillImage = fillImageBorder == null ? null : fillImageBorder.getFillImage(FillImageBorder.TOP_LEFT);
            return fillImage == null ? null : fillImage.getImage();
        } else if (IMAGE_ID_BORDER_TOP.equals(imageId)) {
            FillImageBorder fillImageBorder = ((FillImageBorder) component.getRenderProperty(BorderPane.PROPERTY_BORDER));
            FillImage fillImage = fillImageBorder == null ? null : fillImageBorder.getFillImage(FillImageBorder.TOP);
            return fillImage == null ? null : fillImage.getImage();
        } else if (IMAGE_ID_BORDER_TOP_RIGHT.equals(imageId)) {
            FillImageBorder fillImageBorder = ((FillImageBorder) component.getRenderProperty(BorderPane.PROPERTY_BORDER));
            FillImage fillImage = fillImageBorder == null ? null : fillImageBorder.getFillImage(FillImageBorder.TOP_RIGHT);
            return fillImage == null ? null : fillImage.getImage();
        } else if (IMAGE_ID_BORDER_LEFT.equals(imageId)) {
            FillImageBorder fillImageBorder = ((FillImageBorder) component.getRenderProperty(BorderPane.PROPERTY_BORDER));
            FillImage fillImage = fillImageBorder == null ? null : fillImageBorder.getFillImage(FillImageBorder.LEFT);
            return fillImage == null ? null : fillImage.getImage();
        } else if (IMAGE_ID_BORDER_RIGHT.equals(imageId)) {
            FillImageBorder fillImageBorder = ((FillImageBorder) component.getRenderProperty(BorderPane.PROPERTY_BORDER));
            FillImage fillImage = fillImageBorder == null ? null : fillImageBorder.getFillImage(FillImageBorder.RIGHT);
            return fillImage == null ? null : fillImage.getImage();
        } else if (IMAGE_ID_BORDER_BOTTOM_LEFT.equals(imageId)) {
            FillImageBorder fillImageBorder = ((FillImageBorder) component.getRenderProperty(BorderPane.PROPERTY_BORDER));
            FillImage fillImage = fillImageBorder == null ? null : fillImageBorder.getFillImage(FillImageBorder.BOTTOM_LEFT);
            return fillImage == null ? null : fillImage.getImage();
        } else if (IMAGE_ID_BORDER_BOTTOM.equals(imageId)) {
            FillImageBorder fillImageBorder = ((FillImageBorder) component.getRenderProperty(BorderPane.PROPERTY_BORDER));
            FillImage fillImage = fillImageBorder == null ? null : fillImageBorder.getFillImage(FillImageBorder.BOTTOM);
            return fillImage == null ? null : fillImage.getImage();
        } else if (IMAGE_ID_BORDER_BOTTOM_RIGHT.equals(imageId)) {
            FillImageBorder fillImageBorder = ((FillImageBorder) component.getRenderProperty(BorderPane.PROPERTY_BORDER));
            FillImage fillImage = fillImageBorder == null ? null : fillImageBorder.getFillImage(FillImageBorder.BOTTOM_RIGHT);
            return fillImage == null ? null : fillImage.getImage();
        } else {
            return null;
        }
    }

    /**
     * @see nextapp.echo2.webcontainer.ComponentSynchronizePeer#renderAdd(nextapp.echo2.webcontainer.RenderContext,
     *      nextapp.echo2.app.update.ServerComponentUpdate, java.lang.String,
     *      nextapp.echo2.app.Component)
     */
    public void renderAdd(RenderContext rc, ServerComponentUpdate update, String targetId, Component component) {
        DocumentFragment htmlFragment = rc.getServerMessage().getDocument().createDocumentFragment();
        renderHtml(rc, update, htmlFragment, component);
        DomUpdate.renderElementAdd(rc.getServerMessage(), targetId, htmlFragment);
    }

    /**
     * Renders the <code>FillImageBorder</code> as HTML.
     * 
     * @param rc the relevant <code>RenderContext</code>
     * @param windowDivElement the main DIV element of the window
     * @param borderPane the <code>WindowPane</code> instance
     */
    private void renderBorder(RenderContext rc, Element windowDivElement, BorderPane borderPane) {
        FillImageBorder border = (FillImageBorder) borderPane.getRenderProperty(BorderPane.PROPERTY_BORDER, DEFAULT_BORDER);
        Color borderColor = border.getColor();
        Insets borderInsets = border.getBorderInsets() == null ? new Insets(0) : border.getBorderInsets();
        Document document = rc.getServerMessage().getDocument();
        String elementId = ContainerInstance.getElementId(borderPane);

        boolean renderPositioningBothSides = !rc.getContainerInstance().getClientProperties().getBoolean(
                ClientProperties.QUIRK_CSS_POSITIONING_ONE_SIDE_ONLY);
        boolean renderSizeExpression = !renderPositioningBothSides
                && rc.getContainerInstance().getClientProperties().getBoolean(
                        ClientProperties.PROPRIETARY_IE_CSS_EXPRESSIONS_SUPPORTED);
        int borderTopPixels = ExtentRender.toPixels(borderInsets.getTop(), 0);
        int borderLeftPixels = ExtentRender.toPixels(borderInsets.getLeft(), 0);
        int borderRightPixels = ExtentRender.toPixels(borderInsets.getRight(), 0);
        int borderBottomPixels = ExtentRender.toPixels(borderInsets.getBottom(), 0);
        int borderVerticalPixels = borderTopPixels + borderBottomPixels;
        int borderHorizontalPixels = borderLeftPixels + borderRightPixels;
        
        int fillImageRenderFlags = 0;

        // Top Left Corner
        Element borderDivElement = document.createElement("div");
        borderDivElement.setAttribute("id", elementId + "_border_tl");
        CssStyle borderCssStyle = new CssStyle();
        borderCssStyle.setAttribute("font-size", "1px");
        ColorRender.renderToStyle(borderCssStyle, null, borderColor);
        borderCssStyle.setAttribute("position", "absolute");
        borderCssStyle.setAttribute("top", "0px");
        borderCssStyle.setAttribute("left", "0px");
        borderCssStyle.setAttribute("height", borderTopPixels + "px");
        borderCssStyle.setAttribute("width", borderLeftPixels + "px");
        FillImageRender.renderToStyle(borderCssStyle, rc, this, borderPane, IMAGE_ID_BORDER_TOP_LEFT, border
                .getFillImage(FillImageBorder.TOP_LEFT), fillImageRenderFlags);
        borderDivElement.setAttribute("style", borderCssStyle.renderInline());
        windowDivElement.appendChild(borderDivElement);

        // Top Side
        borderDivElement = document.createElement("div");
        borderDivElement.setAttribute("id", elementId + "_border_t");
        borderCssStyle = new CssStyle();
        borderCssStyle.setAttribute("font-size", "1px");
        ColorRender.renderToStyle(borderCssStyle, null, borderColor);
        borderCssStyle.setAttribute("position", "absolute");
        borderCssStyle.setAttribute("top", "0px");
        borderCssStyle.setAttribute("left", borderLeftPixels + "px");
        borderCssStyle.setAttribute("height", borderTopPixels + "px");
        if (renderPositioningBothSides) {
            borderCssStyle.setAttribute("right", borderRightPixels + "px");
        } else if (renderSizeExpression) {
            borderCssStyle.setAttribute("width", "expression((document.getElementById('" + elementId + "').clientWidth-"
                    + (borderHorizontalPixels) + ")+'px')");
        }
        FillImageRender.renderToStyle(borderCssStyle, rc, this, borderPane, IMAGE_ID_BORDER_TOP, border
                .getFillImage(FillImageBorder.TOP), fillImageRenderFlags);
        borderDivElement.setAttribute("style", borderCssStyle.renderInline());
        windowDivElement.appendChild(borderDivElement);

        // Top Right Corner
        borderDivElement = document.createElement("div");
        borderDivElement.setAttribute("id", elementId + "_border_tr");
        borderCssStyle = new CssStyle();
        borderCssStyle.setAttribute("font-size", "1px");
        ColorRender.renderToStyle(borderCssStyle, null, borderColor);
        borderCssStyle.setAttribute("position", "absolute");
        borderCssStyle.setAttribute("top", "0px");
        borderCssStyle.setAttribute("right", "0px");
        borderCssStyle.setAttribute("height", borderTopPixels + "px");
        borderCssStyle.setAttribute("width", borderRightPixels + "px");
        FillImageRender.renderToStyle(borderCssStyle, rc, this, borderPane, IMAGE_ID_BORDER_TOP_RIGHT, border
                .getFillImage(FillImageBorder.TOP_RIGHT), fillImageRenderFlags);
        borderDivElement.setAttribute("style", borderCssStyle.renderInline());
        windowDivElement.appendChild(borderDivElement);

        // Left Side
        borderDivElement = document.createElement("div");
        borderDivElement.setAttribute("id", elementId + "_border_l");
        borderCssStyle = new CssStyle();
        borderCssStyle.setAttribute("font-size", "1px");
        ColorRender.renderToStyle(borderCssStyle, null, borderColor);
        borderCssStyle.setAttribute("position", "absolute");
        borderCssStyle.setAttribute("top", borderTopPixels + "px");
        borderCssStyle.setAttribute("left", "0px");
        borderCssStyle.setAttribute("width", borderLeftPixels + "px");
        if (renderPositioningBothSides) {
            borderCssStyle.setAttribute("bottom", borderRightPixels + "px");
        } else if (renderSizeExpression) {
            borderCssStyle.setAttribute("height", "expression((document.getElementById('" + elementId + "').clientHeight-"
                    + (borderVerticalPixels) + ")+'px')");

        }
        FillImageRender.renderToStyle(borderCssStyle, rc, this, borderPane, IMAGE_ID_BORDER_LEFT, border
                .getFillImage(FillImageBorder.LEFT), fillImageRenderFlags);
        borderDivElement.setAttribute("style", borderCssStyle.renderInline());
        windowDivElement.appendChild(borderDivElement);

        // Right Side
        borderDivElement = document.createElement("div");
        borderDivElement.setAttribute("id", elementId + "_border_r");
        borderCssStyle = new CssStyle();
        borderCssStyle.setAttribute("font-size", "1px");
        ColorRender.renderToStyle(borderCssStyle, null, borderColor);
        borderCssStyle.setAttribute("position", "absolute");
        borderCssStyle.setAttribute("top", borderTopPixels + "px");
        borderCssStyle.setAttribute("right", "0px");
        borderCssStyle.setAttribute("width", borderRightPixels + "px");
        if (renderPositioningBothSides) {
            borderCssStyle.setAttribute("bottom", borderRightPixels + "px");
        } else if (renderSizeExpression) {
            borderCssStyle.setAttribute("height", "expression((document.getElementById('" + elementId + "').clientHeight-"
                    + (borderVerticalPixels) + ")+'px')");

        }
        FillImageRender.renderToStyle(borderCssStyle, rc, this, borderPane, IMAGE_ID_BORDER_RIGHT, border
                .getFillImage(FillImageBorder.RIGHT), fillImageRenderFlags);
        borderDivElement.setAttribute("style", borderCssStyle.renderInline());
        windowDivElement.appendChild(borderDivElement);

        // Bottom Left Corner
        borderDivElement = document.createElement("div");
        borderDivElement.setAttribute("id", elementId + "_border_bl");
        borderCssStyle = new CssStyle();
        borderCssStyle.setAttribute("font-size", "1px");
        ColorRender.renderToStyle(borderCssStyle, null, borderColor);
        borderCssStyle.setAttribute("position", "absolute");
        borderCssStyle.setAttribute("bottom", "0px");
        borderCssStyle.setAttribute("left", "0px");
        borderCssStyle.setAttribute("height", borderBottomPixels + "px");
        borderCssStyle.setAttribute("width", borderLeftPixels + "px");
        FillImageRender.renderToStyle(borderCssStyle, rc, this, borderPane, IMAGE_ID_BORDER_BOTTOM_LEFT, border
                .getFillImage(FillImageBorder.BOTTOM_LEFT), fillImageRenderFlags);
        borderDivElement.setAttribute("style", borderCssStyle.renderInline());
        windowDivElement.appendChild(borderDivElement);

        // Bottom Side
        borderDivElement = document.createElement("div");
        borderDivElement.setAttribute("id", elementId + "_border_b");
        borderCssStyle = new CssStyle();
        borderCssStyle.setAttribute("font-size", "1px");
        ColorRender.renderToStyle(borderCssStyle, null, borderColor);
        borderCssStyle.setAttribute("position", "absolute");
        borderCssStyle.setAttribute("bottom", "0px");
        borderCssStyle.setAttribute("left", borderLeftPixels + "px");
        borderCssStyle.setAttribute("height", borderBottomPixels + "px");
        if (renderPositioningBothSides) {
            borderCssStyle.setAttribute("right", borderRightPixels + "px");
        } else if (renderSizeExpression) {
            borderCssStyle.setAttribute("width", "expression((document.getElementById('" + elementId + "').clientWidth-"
                    + (borderHorizontalPixels) + ")+'px')");

        }
        FillImageRender.renderToStyle(borderCssStyle, rc, this, borderPane, IMAGE_ID_BORDER_BOTTOM, border
                .getFillImage(FillImageBorder.BOTTOM), fillImageRenderFlags);
        borderDivElement.setAttribute("style", borderCssStyle.renderInline());
        windowDivElement.appendChild(borderDivElement);

        // Bottom Right Side
        borderDivElement = document.createElement("div");
        borderDivElement.setAttribute("id", elementId + "_border_br");
        borderCssStyle = new CssStyle();
        borderCssStyle.setAttribute("font-size", "1px");
        ColorRender.renderToStyle(borderCssStyle, null, borderColor);
        borderCssStyle.setAttribute("position", "absolute");
        borderCssStyle.setAttribute("bottom", "0px");
        borderCssStyle.setAttribute("right", "0px");
        borderCssStyle.setAttribute("height", borderBottomPixels + "px");
        borderCssStyle.setAttribute("width", borderRightPixels + "px");
        FillImageRender.renderToStyle(borderCssStyle, rc, this, borderPane, IMAGE_ID_BORDER_BOTTOM_RIGHT, border
                .getFillImage(FillImageBorder.BOTTOM_RIGHT), fillImageRenderFlags);
        borderDivElement.setAttribute("style", borderCssStyle.renderInline());
        windowDivElement.appendChild(borderDivElement);
    }
    
    /**
     * @see nextapp.echo2.webcontainer.ComponentSynchronizePeer#renderDispose(nextapp.echo2.webcontainer.RenderContext,
     *      nextapp.echo2.app.update.ServerComponentUpdate,
     *      nextapp.echo2.app.Component)
     */
    public void renderDispose(RenderContext rc, ServerComponentUpdate update, Component component) {
        // TODO Auto-generated method stub
    }

    /**
     * @see nextapp.echo2.webcontainer.DomUpdateSupport#renderHtml(nextapp.echo2.webcontainer.RenderContext, 
     *      nextapp.echo2.app.update.ServerComponentUpdate, org.w3c.dom.Node, nextapp.echo2.app.Component)
     */
    public void renderHtml(RenderContext rc, ServerComponentUpdate update, Node parentNode, Component component) {
        BorderPane borderPane = (BorderPane) component;
        String elementId = ContainerInstance.getElementId(borderPane);
        String bodyElementId = elementId + "_body";
        boolean renderPositioningBothSides = !rc.getContainerInstance().getClientProperties().getBoolean(
                ClientProperties.QUIRK_CSS_POSITIONING_ONE_SIDE_ONLY);
        boolean renderSizeExpression = !renderPositioningBothSides
                && rc.getContainerInstance().getClientProperties().getBoolean(
                        ClientProperties.PROPRIETARY_IE_CSS_EXPRESSIONS_SUPPORTED);
        Component child = null;
        if (borderPane.getComponentCount() != 0) {
            child = borderPane.getComponent(0);
        }

        ServerMessage serverMessage = rc.getServerMessage();
        Document document = serverMessage.getDocument();
        Element borderPaneDivElement = document.createElement("div");
        borderPaneDivElement.setAttribute("id", elementId);

        // Create main window DIV element.
        CssStyle borderPaneDivCssStyle = new CssStyle();
        borderPaneDivCssStyle.setAttribute("padding", "0px");
        borderPaneDivCssStyle.setAttribute("position", "absolute");
        borderPaneDivCssStyle.setAttribute("top", "0px");
        borderPaneDivCssStyle.setAttribute("left", "0px");
        borderPaneDivCssStyle.setAttribute("width", "100%");
        borderPaneDivCssStyle.setAttribute("height", "100%");
        borderPaneDivElement.setAttribute("style", borderPaneDivCssStyle.renderInline());
        parentNode.appendChild(borderPaneDivElement);

        // Render BorderPane border.
        renderBorder(rc, borderPaneDivElement, borderPane);

        // Create BorderPane body DIV element (container of title and content
        // areas).
        Element borderPaneBodyDivElement = document.createElement("div");
        borderPaneBodyDivElement.setAttribute("id", bodyElementId);
        CssStyle windowBodyDivCssStyle = new CssStyle();
        ColorRender.renderToStyle(windowBodyDivCssStyle, (Color) borderPane.getRenderProperty(BorderPane.PROPERTY_FOREGROUND),
                (Color) borderPane.getRenderProperty(BorderPane.PROPERTY_BACKGROUND));
        FontRender.renderToStyle(windowBodyDivCssStyle, (Font) borderPane.getRenderProperty(BorderPane.PROPERTY_FONT));
        if (windowBodyDivCssStyle.getAttribute("background-color") == null) {
            windowBodyDivCssStyle.setAttribute("background-color", "white");
        }
        windowBodyDivCssStyle.setAttribute("position", "absolute");
        windowBodyDivCssStyle.setAttribute("z-index", "2");
        FillImageBorder border = (FillImageBorder) borderPane.getRenderProperty(BorderPane.PROPERTY_BORDER, DEFAULT_BORDER);
        Insets contentInsets = border.getContentInsets() == null ? new Insets(0) : border.getContentInsets();
        int left = ExtentRender.toPixels(contentInsets.getLeft(), 0);
        int right = ExtentRender.toPixels(contentInsets.getRight(), 0);
        int top = ExtentRender.toPixels(contentInsets.getTop(), 0);
        int bottom = ExtentRender.toPixels(contentInsets.getBottom(), 0);
        windowBodyDivCssStyle.setAttribute("top", top + "px");
        windowBodyDivCssStyle.setAttribute("left", left + "px");
        if (renderPositioningBothSides) {
            windowBodyDivCssStyle.setAttribute("bottom", bottom + "px");
            windowBodyDivCssStyle.setAttribute("right", right + "px");
        } else if (renderSizeExpression) {
            windowBodyDivCssStyle.setAttribute("height", "expression((document.getElementById('" + elementId + "').clientHeight-"
                    + (top + bottom) + ")+'px')");
            windowBodyDivCssStyle.setAttribute("width", "expression((document.getElementById('" + elementId + "').clientWidth-"
                    + (left + right) + ")+'px')");
        }
        borderPaneBodyDivElement.setAttribute("style", windowBodyDivCssStyle.renderInline());
        borderPaneDivElement.appendChild(borderPaneBodyDivElement);

        // Create inset content DIV Element.
        Element contentDivElement = document.createElement("div");
        contentDivElement.setAttribute("id", elementId + "_content");
        CssStyle contentDivCssStyle = new CssStyle();
        if (!(child instanceof Pane)) {
            // Render inset padding only if pane content is not itself a Pane.
            InsetsRender.renderToStyle(contentDivCssStyle, "padding", 
                    (Insets) borderPane.getRenderProperty(BorderPane.PROPERTY_INSETS));
        }
        contentDivElement.setAttribute("style", contentDivCssStyle.renderInline());
        borderPaneBodyDivElement.appendChild(contentDivElement);
        
        // Render child.
        if (child != null) {
            ComponentSynchronizePeer syncPeer = SynchronizePeerFactory.getPeerForComponent(child.getClass());
            if (syncPeer instanceof DomUpdateSupport) {
                ((DomUpdateSupport) syncPeer).renderHtml(rc, update, contentDivElement, child);
            } else {
                syncPeer.renderAdd(rc, update, getContainerId(child), child);
            }
        }
    }

    /**
     * @see nextapp.echo2.webcontainer.ComponentSynchronizePeer#renderUpdate(nextapp.echo2.webcontainer.RenderContext,
     *      nextapp.echo2.app.update.ServerComponentUpdate, java.lang.String)
     */
    public boolean renderUpdate(RenderContext rc, ServerComponentUpdate update, String targetId) {
        DomUpdate.renderElementRemove(rc.getServerMessage(), ContainerInstance.getElementId(update.getParent()));
        renderAdd(rc, update, targetId, update.getParent());
        return true;
    }
}