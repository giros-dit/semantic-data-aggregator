from .ngsi_ld.entity import Entity, Property, Relationship
from typing import Literal, Optional


class VNF(Entity):
    type: Literal["VNF"] = "VNF"
    descriptorId: Property
    hasMetrics: Optional[Relationship] = None
