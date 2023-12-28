import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IIndividualAssessment } from '../individual-assessment.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../individual-assessment.test-samples';

import { IndividualAssessmentService } from './individual-assessment.service';

const requireRestSample: IIndividualAssessment = {
  ...sampleWithRequiredData,
};

describe('IndividualAssessment Service', () => {
  let service: IndividualAssessmentService;
  let httpMock: HttpTestingController;
  let expectedResult: IIndividualAssessment | IIndividualAssessment[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(IndividualAssessmentService);
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

    it('should create a IndividualAssessment', () => {
      const individualAssessment = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(individualAssessment).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a IndividualAssessment', () => {
      const individualAssessment = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(individualAssessment).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a IndividualAssessment', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of IndividualAssessment', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a IndividualAssessment', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addIndividualAssessmentToCollectionIfMissing', () => {
      it('should add a IndividualAssessment to an empty array', () => {
        const individualAssessment: IIndividualAssessment = sampleWithRequiredData;
        expectedResult = service.addIndividualAssessmentToCollectionIfMissing([], individualAssessment);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(individualAssessment);
      });

      it('should not add a IndividualAssessment to an array that contains it', () => {
        const individualAssessment: IIndividualAssessment = sampleWithRequiredData;
        const individualAssessmentCollection: IIndividualAssessment[] = [
          {
            ...individualAssessment,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addIndividualAssessmentToCollectionIfMissing(individualAssessmentCollection, individualAssessment);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a IndividualAssessment to an array that doesn't contain it", () => {
        const individualAssessment: IIndividualAssessment = sampleWithRequiredData;
        const individualAssessmentCollection: IIndividualAssessment[] = [sampleWithPartialData];
        expectedResult = service.addIndividualAssessmentToCollectionIfMissing(individualAssessmentCollection, individualAssessment);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(individualAssessment);
      });

      it('should add only unique IndividualAssessment to an array', () => {
        const individualAssessmentArray: IIndividualAssessment[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const individualAssessmentCollection: IIndividualAssessment[] = [sampleWithRequiredData];
        expectedResult = service.addIndividualAssessmentToCollectionIfMissing(individualAssessmentCollection, ...individualAssessmentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const individualAssessment: IIndividualAssessment = sampleWithRequiredData;
        const individualAssessment2: IIndividualAssessment = sampleWithPartialData;
        expectedResult = service.addIndividualAssessmentToCollectionIfMissing([], individualAssessment, individualAssessment2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(individualAssessment);
        expect(expectedResult).toContain(individualAssessment2);
      });

      it('should accept null and undefined values', () => {
        const individualAssessment: IIndividualAssessment = sampleWithRequiredData;
        expectedResult = service.addIndividualAssessmentToCollectionIfMissing([], null, individualAssessment, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(individualAssessment);
      });

      it('should return initial array if no IndividualAssessment is added', () => {
        const individualAssessmentCollection: IIndividualAssessment[] = [sampleWithRequiredData];
        expectedResult = service.addIndividualAssessmentToCollectionIfMissing(individualAssessmentCollection, undefined, null);
        expect(expectedResult).toEqual(individualAssessmentCollection);
      });
    });

    describe('compareIndividualAssessment', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareIndividualAssessment(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareIndividualAssessment(entity1, entity2);
        const compareResult2 = service.compareIndividualAssessment(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareIndividualAssessment(entity1, entity2);
        const compareResult2 = service.compareIndividualAssessment(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareIndividualAssessment(entity1, entity2);
        const compareResult2 = service.compareIndividualAssessment(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
