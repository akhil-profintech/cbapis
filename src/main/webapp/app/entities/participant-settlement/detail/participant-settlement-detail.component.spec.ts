import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ParticipantSettlementDetailComponent } from './participant-settlement-detail.component';

describe('ParticipantSettlement Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParticipantSettlementDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ParticipantSettlementDetailComponent,
              resolve: { participantSettlement: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ParticipantSettlementDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load participantSettlement on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ParticipantSettlementDetailComponent);

      // THEN
      expect(instance.participantSettlement).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
