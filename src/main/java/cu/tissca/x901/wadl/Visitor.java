package cu.tissca.x901.wadl;

import cu.tissca.x901.wadl.model.*;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public interface Visitor {

    void visitWebApplicationDescriptor(WebApplicationElement webApplicationElement);

    void endVisitWebApplicationDescriptor();

    void visitResourceDescriptor(ResourceElement resourceElement);

    void endVisitResourceDescriptor(ResourceElement resourceElement);

    void visitMethodDescriptor(MethodElement methodElement);

    void endVisitMethodDescriptor(MethodElement methodElement);

    void visitRequestDescriptor(RequestElement requestDescriptor);

    void endVisitRequestDescriptor(RequestElement requestDescriptor);

    void visitDocElement(DocElement docElement);

    void endVisitDocElement(DocElement docElement);

    void visitParamDescriptor(ParamElement paramElement);

    void endVisitParamDescriptor(ParamElement paramElement);

    void visitResponseDescriptor(ResponseElement responseElement);

    void endVisitResponseDescriptor(ResponseElement responseElement);

    void visitRepresentation(RepresentationElement representationElement);

    void endVisitRepresentation(RepresentationElement representationElement);
}
