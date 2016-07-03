package cu.tissca.x901.wad;

import cu.tissca.x901.wad.model.*;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public interface Visitor {

    void visitWebApplicationDescriptor(WebApplicationDescriptor webApplicationDescriptor);

    void endVisitWebApplicationDescriptor();

    void visitResourceDescriptor(ResourceDescriptor resourceDescriptor);

    void endVisitResourceDescriptor(ResourceDescriptor resourceDescriptor);

    void visitMethodDescriptor(MethodDescriptor methodDescriptor);

    void endVisitMethodDescriptor(MethodDescriptor methodDescriptor);

    void visitRequestDescriptor(RequestDescriptor requestDescriptor);

    void endVisitRequestDescriptor(RequestDescriptor requestDescriptor);

    void visitDocElement(DocElement docElement);

    void endVisitDocElement(DocElement docElement);

    void visitParamDescriptor(ParamDescriptor paramDescriptor);

    void endVisitParamDescriptor(ParamDescriptor paramDescriptor);

    void visitResponseDescriptor(ResponseDescriptor responseDescriptor);

    void endVisitResponseDescriptor(ResponseDescriptor responseDescriptor);

    void visitRepresentation(RepresentationDescriptor representationDescriptor);

    void endVisitRepresentation(RepresentationDescriptor representationDescriptor);
}
