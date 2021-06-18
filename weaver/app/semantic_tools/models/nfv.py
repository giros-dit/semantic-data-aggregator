from .ngsi_ld.entity import Asset, Property, Relationship
from typing import Literal, Optional


class VNF(Asset):
    type: Literal["VNF"] = "VNF"
    descriptorId: Property
    hasMetrics: Optional[Relationship] = None
