import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IParticipantSettlement } from '../participant-settlement.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../participant-settlement.test-samples';

import { ParticipantSettlementService } from './participant-settlement.service';

const requireRestSample: IParticipantSettlement = {
  ...sampleWithRequiredData,
};

describe('ParticipantSettlement Service', () => {
  let service: ParticipantSettlementService;
  let httpMock: HttpTestingController;
  let expectedResult: IParticipantSettlement | IParticipantSettlement[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ParticipantSettlementService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a ParticipantSettlement', () => {
      const participantSettlement = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(participantSettlement).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ParticipantSettlement', () => {
      const participantSettlement = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(participantSettlement).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ParticipantSettlement', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ParticipantSettlement', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ParticipantSettlement', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addParticipantSettlementToCollectionIfMissing', () => {
      it('should add a ParticipantSettlement to an empty array', () => {
        const participantSettlement: IParticipantSettlement = sampleWithRequiredData;
        expectedResult = service.addParticipantSettlementToCollectionIfMissing([], participantSettlement);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(participantSettlement);
      });

      it('should not add a ParticipantSettlement to an array that contains it', () => {
        const participantSettlement: IParticipantSettlement = sampleWithRequiredData;
        const participantSettlementCollection: IParticipantSettlement[] = [
          {
            ...participantSettlement,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addParticipantSettlementToCollectionIfMissing(participantSettlementCollection, participantSettlement);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ParticipantSettlement to an array that doesn't contain it", () => {
        const participantSettlement: IParticipantSettlement = sampleWithRequiredData;
        const participantSettlementCollection: IParticipantSettlement[] = [sampleWithPartialData];
        expectedResult = service.addParticipantSettlementToCollectionIfMissing(participantSettlementCollection, participantSettlement);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(participantSettlement);
      });

      it('should add only unique ParticipantSettlement to an array', () => {
        const participantSettlementArray: IParticipantSettlement[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const participantSettlementCollection: IParticipantSettlement[] = [sampleWithRequiredData];
        expectedResult = service.addParticipantSettlementToCollectionIfMissing(
          participantSettlementCollection,
          ...participantSettlementArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const participantSettlement: IParticipantSettlement = sampleWithRequiredData;
        const participantSettlement2: IParticipantSettlement = sampleWithPartialData;
        expectedResult = service.addParticipantSettlementToCollectionIfMissing([], participantSettlement, participantSettlement2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(participantSettlement);
        expect(expectedResult).toContain(participantSettlement2);
      });

      it('should accept null and undefined values', () => {
        const participantSettlement: IParticipantSettlement = sampleWithRequiredData;
        expectedResult = service.addParticipantSettlementToCollectionIfMissing([], null, participantSettlement, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(participantSettlement);
      });

      it('should return initial array if no ParticipantSettlement is added', () => {
        const participantSettlementCollection: IParticipantSettlement[] = [sampleWithRequiredData];
        expectedResult = service.addParticipantSettlementToCollectionIfMissing(participantSettlementCollection, undefined, null);
        expect(expectedResult).toEqual(participantSettlementCollection);
      });
    });

    describe('compareParticipantSettlement', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareParticipantSettlement(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareParticipantSettlement(entity1, entity2);
        const compareResult2 = service.compareParticipantSettlement(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareParticipantSettlement(entity1, entity2);
        const compareResult2 = service.compareParticipantSettlement(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareParticipantSettlement(entity1, entity2);
        const compareResult2 = service.compareParticipantSettlement(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
