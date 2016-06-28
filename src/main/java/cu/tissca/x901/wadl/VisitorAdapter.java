package cu.tissca.x901.wadl;

import cu.tissca.x901.wadl.model.*;

/**
 * An abstract Visitor implementation that does nothing.
 * The methods on this class are empty. This class exists as a convenience to implement Visitors.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class VisitorAdapter implements Visitor {
    @Override
    public void visitWebApplicationDescriptor(WebApplicationElement webApplicationElement) {}

    @Override
    public void endVisitWebApplicationDescriptor() {}

    @Override
    public void visitResourceDescriptor(ResourceElement resourceElement) {}

    @Override
    public void endVisitResourceDescriptor(ResourceElement resourceElement) {}

    @Override
    public void visitMethodDescriptor(MethodElement methodElement) {}

    @Override
    public void endVisitMethodDescriptor(MethodElement methodElement) {}

    @Override
    public void visitRequestDescriptor(RequestElement requestDescriptor) {}

    @Override
    public void endVisitRequestDescriptor(RequestElement requestDescriptor) {}

    @Override
    public void visitDocElement(DocElement docElement) {}

    @Override
    public void endVisitDocElement(DocElement docElement) {}

    @Override
    public void visitParamDescriptor(ParamElement paramElement) {}

    @Override
    public void endVisitParamDescriptor(ParamElement paramElement) {}

    @Override
    public void visitResponseDescriptor(ResponseElement responseElement) {}

    @Override
    public void endVisitResponseDescriptor(ResponseElement responseElement) {}

    @Override
    public void visitRepresentation(RepresentationElement representationElement) {}

    @Override
    public void endVisitRepresentation(RepresentationElement representationElement) {}
}
