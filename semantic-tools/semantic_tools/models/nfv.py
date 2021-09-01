from semantic_tools.models.common import Asset
from semantic_tools.models.ngsi_ld.entity import Property, Relationship
from typing import Literal, Optional


class VNF(Asset):
    type: Literal["VNF"] = "VNF"
    descriptorId: Property
    hasMetrics: Optional[Relationship] = None
