package cu.tissca.x901.wad;

import cu.tissca.x901.wad.model.*;

/**
 * An abstract Visitor implementation that does nothing.
 * The methods on this class are empty. This class exists as a convenience to implement Visitors.
 *
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class VisitorAdapter implements Visitor {
    @Override
    public void visitWebApplicationDescriptor(WebApplicationDescriptor webApplicationDescriptor) {}

    @Override
    public void endVisitWebApplicationDescriptor() {}

    @Override
    public void visitResourceDescriptor(ResourceDescriptor resourceDescriptor) {}

    @Override
    public void endVisitResourceDescriptor(ResourceDescriptor resourceDescriptor) {}

    @Override
    public void visitMethodDescriptor(MethodDescriptor methodDescriptor) {}

    @Override
    public void endVisitMethodDescriptor(MethodDescriptor methodDescriptor) {}

    @Override
    public void visitRequestDescriptor(RequestDescriptor requestDescriptor) {}

    @Override
    public void endVisitRequestDescriptor(RequestDescriptor requestDescriptor) {}

    @Override
    public void visitDocElement(DocElement docElement) {}

    @Override
    public void endVisitDocElement(DocElement docElement) {}

    @Override
    public void visitParamDescriptor(ParamDescriptor paramDescriptor) {}

    @Override
    public void endVisitParamDescriptor(ParamDescriptor paramDescriptor) {}

    @Override
    public void visitResponseDescriptor(ResponseDescriptor responseDescriptor) {}

    @Override
    public void endVisitResponseDescriptor(ResponseDescriptor responseDescriptor) {}

    @Override
    public void visitRepresentation(RepresentationDescriptor representationDescriptor) {}

    @Override
    public void endVisitRepresentation(RepresentationDescriptor representationDescriptor) {}
}
