ExtrasColorSelect = function(elementId, containerElementId) {
    this.elementId = elementId;
    this.containerElementId = containerElementId;
    this.svGradientImageSrc = "SVTransparentGradient.png";
    this.hGradientImageSrc = "HGradient.png";
    this.vLineImageSrc = "VLine.gif";
    this.sLineImageSrc = "SLine.gif";
    this.hLineImageSrc = "HLine.gif";
    this.h = 0;
    this.s = 1;
    this.v = 1;
    this.enableInternetExplorerPngWorkaround = false;
    
    this.hActive = false;
    this.svActive = false;
};

ExtrasColorSelect.prototype.create = function() {
    var containerElement = document.getElementById(this.containerElementId);
    var colorSelectDivElement = document.createElement("div");
    colorSelectDivElement.id = this.elementId;
    colorSelectDivElement.style.position = "relative";
    colorSelectDivElement.style.left = "0px";
    colorSelectDivElement.style.top = "0px";
    colorSelectDivElement.style.width = "199px";
    colorSelectDivElement.style.height = "186px";
    colorSelectDivElement.style.overflow = "hidden";
    
    var svDivElement = document.createElement("div");
    svDivElement.id = this.elementId + "_sv";
    svDivElement.style.position = "absolute";
    svDivElement.style.left = "7px";
    svDivElement.style.top = "7px";
    svDivElement.style.width = "150px";
    svDivElement.style.height = "150px";
    svDivElement.style.backgroundColor = "#ff0000";
    colorSelectDivElement.appendChild(svDivElement);

    if (this.enableInternetExplorerPngWorkaround) {
        svDivElement.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader("
                + "src='" + this.svGradientImageSrc + "', sizingMethod='scale');";
    } else {
        var svGradientImgElement = document.createElement("img");
        svGradientImgElement.src = this.svGradientImageSrc;
        svDivElement.appendChild(svGradientImgElement);
    }
    
    var vLineDivElement = document.createElement("div");
    vLineDivElement.id = this.elementId + "_vline";
    vLineDivElement.style.position = "absolute";
    vLineDivElement.style.left = "2px";
    vLineDivElement.style.top = "0px";
    vLineDivElement.style.width = "11px";
    vLineDivElement.style.height = "164px";
    vLineDivElement.style.overflow = "hidden";
    colorSelectDivElement.appendChild(vLineDivElement);

    var vLineImgElement = document.createElement("img");
    vLineImgElement.src = this.vLineImageSrc;
    vLineImgElement.style.position = "absolute";
    vLineImgElement.style.left = "0px";
    vLineImgElement.style.top = "0px";
    vLineDivElement.appendChild(vLineImgElement);

    var sLineDivElement = document.createElement("div");
    sLineDivElement.id = this.elementId + "_sline";
    sLineDivElement.style.position = "absolute";
    sLineDivElement.style.left = "0px";
    sLineDivElement.style.top = "152px";
    sLineDivElement.style.height = "11px";
    sLineDivElement.style.width = "164px";
    sLineDivElement.style.overflow = "hidden";
    colorSelectDivElement.appendChild(sLineDivElement);
    
    var sLineImgElement = document.createElement("img");
    sLineImgElement.src = this.sLineImageSrc;
    sLineImgElement.style.position = "absolute";
    sLineImgElement.style.left = "0px";
    sLineImgElement.style.top = "0px";
    sLineDivElement.appendChild(sLineImgElement);

    var hDivElement = document.createElement("div");
    hDivElement.id = this.elementId + "_h";
    hDivElement.style.position = "absolute";
    hDivElement.style.left = "172px";
    hDivElement.style.top = "7px";
    hDivElement.style.width = "20px";
    hDivElement.style.height = "150px";
    colorSelectDivElement.appendChild(hDivElement);
    
    var hGradientImgElement = document.createElement("img");
    hGradientImgElement.src = this.hGradientImageSrc;
    hGradientImgElement.style.position = "absolute";
    hGradientImgElement.style.left = "0px";
    hGradientImgElement.style.top = "0px";
    hDivElement.appendChild(hGradientImgElement);
    
    var hLineDivElement = document.createElement("div");
    hLineDivElement.id = this.elementId + "_hline";
    hLineDivElement.style.position = "absolute";
    hLineDivElement.style.left = "165px";
    hLineDivElement.style.top = "152px";
    hLineDivElement.style.height = "11px";
    hLineDivElement.style.width = "34px";
    hLineDivElement.style.overflow = "hidden";
    colorSelectDivElement.appendChild(hLineDivElement);
    
    var hLineImgElement = document.createElement("img");
    hLineImgElement.src = this.hLineImageSrc;
    hLineImgElement.style.position = "absolute";
    hLineImgElement.style.left = "0px";
    hLineImgElement.style.top = "0px";
    hLineDivElement.appendChild(hLineImgElement);

    var colorDivElement = document.createElement("div");
    colorDivElement.id = this.elementId + "_color";
    colorDivElement.style.position = "absolute";
    colorDivElement.style.left = "7px";
    colorDivElement.style.top = "166px";
    colorDivElement.style.width = "183px";
    colorDivElement.style.height = "18px";
    colorDivElement.style.color = "#ffffff";
    colorDivElement.style.backgroundColor = "#000000";
    colorDivElement.style.borderColor = "#000000";
    colorDivElement.style.borderStyle = "outset";
    colorDivElement.style.borderWidth = "1px";
    colorDivElement.style.fontFamily = "monospace";
    colorDivElement.style.textAlign = "center";
    colorDivElement.appendChild(document.createTextNode("#000000"));
    colorSelectDivElement.appendChild(colorDivElement);

    var svListenerDivElement = document.createElement("div");
    svListenerDivElement.id = this.elementId + "_svlistener";
    svListenerDivElement.style.position = "absolute";
    svListenerDivElement.style.zIndex = "1";
    svListenerDivElement.style.left = "0px";
    svListenerDivElement.style.top = "0px";
    svListenerDivElement.style.width = "164px";
    svListenerDivElement.style.height = "164px";
    svListenerDivElement.style.cursor = "crosshair";
    svListenerDivElement.style.backgroundImage = "url(Blank.gif)";
    colorSelectDivElement.appendChild(svListenerDivElement);

    var hListenerDivElement = document.createElement("div");
    hListenerDivElement.id = this.elementId + "_hlistener";
    hListenerDivElement.style.position = "absolute";
    hListenerDivElement.style.zIndex = "1";
    hListenerDivElement.style.left = "165px";
    hListenerDivElement.style.top = "0px";
    hListenerDivElement.style.width = "34px";
    hListenerDivElement.style.height = "164px";
    hListenerDivElement.style.cursor = "crosshair";
    hListenerDivElement.style.backgroundImage = "url(Blank.gif)";
    colorSelectDivElement.appendChild(hListenerDivElement);

    containerElement.appendChild(colorSelectDivElement);
    
    EchoDomPropertyStore.setPropertyValue(this.elementId, "component", this);
    
    EchoEventProcessor.addHandler(svListenerDivElement.id, "mousedown", "ExtrasColorSelect.processSVMouseDown");
    EchoEventProcessor.addHandler(svListenerDivElement.id, "mousemove", "ExtrasColorSelect.processSVMouseMove");
    EchoEventProcessor.addHandler(svListenerDivElement.id, "mouseup", "ExtrasColorSelect.processSVMouseUp");
    EchoEventProcessor.addHandler(hListenerDivElement.id, "mousedown", "ExtrasColorSelect.processHMouseDown");
    EchoEventProcessor.addHandler(hListenerDivElement.id, "mousemove", "ExtrasColorSelect.processHMouseMove");
    EchoEventProcessor.addHandler(hListenerDivElement.id, "mouseup", "ExtrasColorSelect.processHMouseUp");
};

ExtrasColorSelect.prototype.dispose = function() {
    EchoEventProcessor.removeHandler(this.elementId + "_svlistener", "mousedown");
    EchoEventProcessor.removeHandler(this.elementId + "_svlistener", "mousemove");
    EchoEventProcessor.removeHandler(this.elementId + "_svlistener", "mouseup");
    EchoEventProcessor.removeHandler(this.elementId + "_hlistener", "mousedown");
    EchoEventProcessor.removeHandler(this.elementId + "_hlistener", "mousemove");
    EchoEventProcessor.removeHandler(this.elementId + "_hlistener", "mouseup");
};

/**
 * Returns the selected color.

 * @return the selected color as an 
 *         <code>ExtrasColorSelect.RGB</code> value.
 */
ExtrasColorSelect.prototype.getColor = function() {
    return ExtrasColorSelect.hsvToRgb(this.h, this.s, this.v);
};

/**
 * Sets the selected color.
 *
 * @param rgb the color to select as an <code>ExtrasColorSelect.RGB</code>
 *            value.
 */
ExtrasColorSelect.prototype.setColor = function(rgb) {
    var r = rgb.r / 255;
    var g = rgb.g / 255;
    var b = rgb.b / 255;
    
    var min = Math.min(r, g, b);
    var max = Math.max(r, g, b);
    this.v = max;
    
    var delta = max - min;
    if (max == 0 || delta == 0) {
        this.s = 0;
        this.h = 0;
    } else {
        this.s = delta / max;
        if (r == max) {
            this.h = 60 * ((g - b) / delta);
        } else if (g == max) {
            this.h = 60 * (2 + (b - r) / delta);
        } else {
            this.h = 60 * (4 + (r - g) / delta);
        }
        if (this.h < 0) {
            this.h += 360;
        }
    }
    this.updateColor();
};

ExtrasColorSelect.prototype.updateColor = function() {
   var svDivElement = document.getElementById(this.elementId + "_sv");
   var baseColor = ExtrasColorSelect.hsvToRgb(this.h, 1, 1);
   svDivElement.style.backgroundColor = baseColor.toHexTriplet();
   
   var colorDivElement = document.getElementById(this.elementId + "_color");
   var renderColor = ExtrasColorSelect.hsvToRgb(this.h, this.s, this.v);
   var renderHexTriplet = renderColor.toHexTriplet();
   colorDivElement.style.backgroundColor = renderHexTriplet;
   colorDivElement.style.borderColor = renderHexTriplet;
   colorDivElement.style.color = this.v < 0.67 ? "#ffffff" : "#000000";
   colorDivElement.childNodes[0].nodeValue = renderHexTriplet;
   
   var sLineElement = document.getElementById(this.elementId + "_sline");
   var sLineTop = parseInt((1 - this.s) * 150) + 2;
   if (sLineTop < 2) {
       sLineTop = 2;
   } else if (sLineTop > 152) {
       sLineTop = 152;
   }
   sLineElement.style.top = sLineTop + "px";

   var vLineElement = document.getElementById(this.elementId + "_vline");
   var vLineLeft = parseInt(this.v * 150) + 2;
   if (vLineLeft < 2) {
       vLineLeft = 2;
   } else if (vLineLeft > 152) {
       vLineLeft = 152;
   }
   vLineElement.style.left = vLineLeft + "px";
   
   var hLineElement = document.getElementById(this.elementId + "_hline");
   var hLineTop = parseInt((360 - this.h) / 360 * 150) + 2;
   if (hLineTop < 2) {
       hLineTop = 2;
   } else if (hLineTop > 152) {
       hLineTop = 152;
   }
   hLineElement.style.top = hLineTop + "px";
   
   this.updateClientMessage(renderColor);
};

/**
 * Updates the component state in the outgoing <code>ClientMessage</code>.
 *
 * @param componentId the id of the Text Component
 */
ExtrasColorSelect.prototype.updateClientMessage = function(color) {
    var colorPropertyElement = EchoClientMessage.createPropertyElement(this.elementId, "color");
    var colorElement = colorPropertyElement.firstChild;
    if (!colorElement) {
        colorElement = EchoClientMessage.messageDocument.createElement("color");
        colorPropertyElement.appendChild(colorElement);
    }
    colorElement.setAttribute("r", color.r);
    colorElement.setAttribute("g", color.g);
    colorElement.setAttribute("b", color.b);
};

ExtrasColorSelect.prototype.processHUpdate = function(echoEvent) {
    var hContainerDivElement = document.getElementById(this.elementId + "_hlistener");
    var bounds = new ExtrasUtil.Bounds(hContainerDivElement);
    this.h = (150 - (echoEvent.clientY - bounds.top - 7)) * 360 / 150;
    this.updateColor();
};

ExtrasColorSelect.prototype.processSVUpdate = function(echoEvent) {
    var svContainerDivElement = document.getElementById(this.elementId + "_svlistener");
    var bounds = new ExtrasUtil.Bounds(svContainerDivElement);
    this.v = (echoEvent.clientX - bounds.left - 7) / 150;
    this.s = 1 - ((echoEvent.clientY - bounds.top - 7) / 150);
    this.updateColor();
};

ExtrasColorSelect.getComponent = function(componentId) {
    return EchoDomPropertyStore.getPropertyValue(componentId, "component");
};

ExtrasColorSelect.hsvToRgb = function(h, s, v) {
    var r, g, b;
    if (s == 0) {
        r = g = b = v;
    } else {
        h /= 60;
        var i = Math.floor(h);
        var f = h - i;
        var p = v * (1 - s);
        var q = v * (1 - s * f);
        var t = v * (1 - s * (1 - f));
        switch (i) {
        case 0:
            r = v;
            g = t;
            b = p;
            break;
        case 1:
            r = q;
            g = v;
            b = p;
            break;
        case 2:
            r = p;
            g = v;
            b = t;
            break;
        case 3:
            r = p;
            g = q;
            b = v;
            break;
        case 4:
            r = t;
            g = p;
            b = v;
            break;
        default:
            r = v;
            g = p;
            b = q;
            break;
        }
    }
    return new ExtrasColorSelect.RGB(Math.round(r * 255), Math.round(g * 255), Math.round(b * 255));
};

ExtrasColorSelect.processSVMouseDown = function(echoEvent) {
    var componentId = EchoDomUtil.getComponentId(echoEvent.registeredTarget.id);
    var colorSelect = ExtrasColorSelect.getComponent(componentId);
    colorSelect.hActive = false;
    colorSelect.svActive = true;
    EchoDomUtil.preventEventDefault(echoEvent);
};

ExtrasColorSelect.processHMouseDown = function(echoEvent) {
    var componentId = EchoDomUtil.getComponentId(echoEvent.registeredTarget.id);
    var colorSelect = ExtrasColorSelect.getComponent(componentId);
    colorSelect.hActive = true;
    colorSelect.svActive = false;
    EchoDomUtil.preventEventDefault(echoEvent);
};

ExtrasColorSelect.processHMouseMove = function(echoEvent) {
    var componentId = EchoDomUtil.getComponentId(echoEvent.registeredTarget.id);
    var colorSelect = ExtrasColorSelect.getComponent(componentId);
    if (colorSelect.hActive) {
        colorSelect.processHUpdate(echoEvent);
    }
};

ExtrasColorSelect.processSVMouseMove = function(echoEvent) {
    var componentId = EchoDomUtil.getComponentId(echoEvent.registeredTarget.id);
    var colorSelect = ExtrasColorSelect.getComponent(componentId);
    if (colorSelect.svActive) {
        colorSelect.processSVUpdate(echoEvent);
    }
};

ExtrasColorSelect.processSVMouseUp = function(echoEvent) {
    var componentId = EchoDomUtil.getComponentId(echoEvent.registeredTarget.id);
    var colorSelect = ExtrasColorSelect.getComponent(componentId);
    if (colorSelect.svActive) {
        colorSelect.processSVUpdate(echoEvent);
    }
    colorSelect.hActive = false;
    colorSelect.svActive = false;
};

ExtrasColorSelect.processHMouseUp = function(echoEvent) {
    var componentId = EchoDomUtil.getComponentId(echoEvent.registeredTarget.id);
    var colorSelect = ExtrasColorSelect.getComponent(componentId);
    if (colorSelect.hActive) {
        colorSelect.processHUpdate(echoEvent);
    }
    colorSelect.hActive = false;
    colorSelect.svActive = false;
};

ExtrasColorSelect.RGB = function(r, g, b) {
    this.r = ExtrasColorSelect.RGB.cleanValue(r);
    this.g = ExtrasColorSelect.RGB.cleanValue(g);
    this.b = ExtrasColorSelect.RGB.cleanValue(b);
};

ExtrasColorSelect.RGB.cleanValue = function(value) {
    value = value ? parseInt(value) : 0;
    if (value < 0) {
        return 0;
    } else if (value > 255) {
        return 255;
    } else {
        return value;
    }
};

ExtrasColorSelect.RGB.prototype.toHexTriplet = function() {
    var rString = this.r.toString(16);
    if (rString.length == 1) {
        rString = "0" + rString;
    }
    var gString = this.g.toString(16);
    if (gString.length == 1) {
        gString = "0" + gString;
    }
    var bString = this.b.toString(16);
    if (bString.length == 1) {
        bString = "0" + bString;
    }
    return "#" + rString + gString + bString;
};

ExtrasColorSelect.RGB.prototype.toString = function() {
    return "(" + this.r + ", " + this.g + ", " + this.b + ")";
};

/**
 * MessageProcessor implementation.
 */
ExtrasColorSelect.MessageProcessor = function() { };

/**
 * MessageProcessor process() implementation 
 * (invoked by ServerMessage processor).
 *
 * @param messagePartElement the <code>message-part</code> element to process.
 */
ExtrasColorSelect.MessageProcessor.process = function(messagePartElement) {
    for (var i = 0; i < messagePartElement.childNodes.length; ++i) {
        if (messagePartElement.childNodes[i].nodeType === 1) {
            switch (messagePartElement.childNodes[i].tagName) {
            case "dispose":
                ExtrasColorSelect.MessageProcessor.processDispose(messagePartElement.childNodes[i]);
                break;
            case "init":
                ExtrasColorSelect.MessageProcessor.processInit(messagePartElement.childNodes[i]);
                break;
            case "set-color":
                ExtrasColorSelect.MessageProcessor.processSetColor(messagePartElement.childNodes[i]);
                break;
            }
        }
    }
};

/**
 * Processes an <code>dispose</code> message to dispose the state of a 
 * ColorSelect component that is being removed.
 *
 * @param disposeMessageElement the <code>dispose</code> element to process
 */
ExtrasColorSelect.MessageProcessor.processDispose = function(disposeMessageElement) {
    var elementId = disposeMessageElement.getAttribute("eid");
    var colorSelect = ExtrasColorSelect.getComponent(elementId);
    colorSelect.dispose();
};

/**
 * Processes an <code>init</code> message to initialize the state of a 
 * ColorSelect component that is being added.
 *
 * @param initMessageElement the <code>init</code> element to process
 */
ExtrasColorSelect.MessageProcessor.processInit = function(initMessageElement) {
    var elementId = initMessageElement.getAttribute("eid");
    var containerElementId = initMessageElement.getAttribute("container-eid");
    var colorSelect = new ExtrasColorSelect(elementId, containerElementId);
    colorSelect.hGradientImageSrc = EchoClientEngine.baseServerUri + "?serviceId=Echo2Extras.ColorSelect.HGradient";
    colorSelect.svGradientImageSrc = EchoClientEngine.baseServerUri + "?serviceId=Echo2Extras.ColorSelect.SVGradient";
    colorSelect.hLineImageSrc = EchoClientEngine.baseServerUri + "?serviceId=Echo2Extras.ColorSelect.HLine";
    colorSelect.sLineImageSrc = EchoClientEngine.baseServerUri + "?serviceId=Echo2Extras.ColorSelect.SLine";
    colorSelect.vLineImageSrc = EchoClientEngine.baseServerUri + "?serviceId=Echo2Extras.ColorSelect.VLine";
    colorSelect.enableInternetExplorerPngWorkaround = 
            EchoClientProperties.get("proprietaryIEPngAlphaFilterRequired") ? true : false;
    colorSelect.create();
};

/**
 * Processes an <code>set-color</code> message to set the selected color of
 * an existing ColorSelect component.
 *
 * @param setColorMessageElement the <code>set-color</code> element to process
 */
ExtrasColorSelect.MessageProcessor.processSetColor = function(setColorMessageElement) {
    var elementId = setColorMessageElement.getAttribute("eid");
    var colorSelect = ExtrasColorSelect.getComponent(elementId);
    var r = parseInt(setColorMessageElement.getAttribute("r"));
    var g = parseInt(setColorMessageElement.getAttribute("g"));
    var b = parseInt(setColorMessageElement.getAttribute("b"));
    colorSelect.setColor(new ExtrasColorSelect.RGB(r, g, b));
};

